package com.sprout.foopoker.fragments;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import android.os.AsyncTask;
import com.sprout.foopoker.fragments.LogViewerFragment.*;
import android.view.*;
import com.sprout.foopoker.R;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;



public class LogViewerFragment extends ListFragment
{
	int mCurCheckPosition = 0;
	
	private static String TAG = "LogViewerFragment";
	private static boolean D = true;

	private LogViewerFragment.CollectLogTask mCollectLogTask = null;

	protected Handler logHandler;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		logHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				addItem(msg.getData().getString("LINE"));
			}
		};
		
		setHasOptionsMenu(true);
		
		// Create the list adapter
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1));
		
		if (savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
		watchLog();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// Inflate the menu items for use in the action bar
		inflater.inflate(R.menu.log_viewer_actions, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.action_clear:
				clearLog();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void log(String str){
		if(D) Log.d(TAG, str);
//		addItem("LOG: " + str);
	}
	
	private void error(String str){
		log(str);
		//TODO: Make toast of error
	}
	
	private void addItem(String str) {
		((ArrayAdapter<String>) getListAdapter()).add(str);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//showDetails(position);
	}
	
	@Override
	public void onStop(){
		super.onStop();
		cancelCollectTask();
	}
	
	@Override
	public void onStart(){
		super.onStart();
		watchLog();
	}
	
	public void clearLog(){
		// Clear the UI
		((ArrayAdapter<String>) getListAdapter()).clear();
		
		//TODO: The below doesn't seem to actually be clearing the log
		// Clear the file itself
		Process process = null;
		try
		{
			String[] command = {"logcat", "-c"};
			process = Runtime.getRuntime().exec(command);
		
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null){ 
				Log.d(TAG, "clear " +line);
			}
		}
		catch (IOException e)
		{
			log(e.getMessage());
		}
		finally {
			if(process != null) process.destroy();
		}
		
		log("Log Cleared");
	}
	
	public void watchLog(){
		watchLog(null, null, null, true);
	}
	public void watchLog(String format, String buffer){
		watchLog(format, buffer, null, true);
	}
	public void watchLog(String format, String buffer, Collection<String> filterSpecs, boolean watch){
		if (mCollectLogTask != null)
			cancelCollectTask();
			
		// Collect paramaters
		ArrayList<String> list = new ArrayList<String>();
        if (format != null){
            list.add("-v");
            list.add(format);
        }

        if (buffer != null){
            list.add("-b");
            list.add(buffer);
        }

        if (filterSpecs != null){
            for (String filterSpec : filterSpecs){
                list.add(filterSpec);
            }
        }
		
		if (!watch)
			list.add("-d");

        mCollectLogTask = new CollectLogTask(list);
		mCollectLogTask.start();
		log("Task Started.");
	}
	
    
    private class CollectLogTask extends Thread
	{

        
		private boolean mCancel = false;
		private ArrayList<String> params;

		public CollectLogTask(ArrayList<String> params){
			this.params = params;
		}
		
		public void cancel(){
			mCancel = true;
		}
		
		public boolean isCancelled(){ return mCancel;}
		
        @Override
        public void run(){
            //final StringBuilder log = new StringBuilder();
			Process process = null;
            try{
                ArrayList<String> commandLine = new ArrayList<String>();
                commandLine.add("logcat");//$NON-NLS-1$
				
                ArrayList<String> arguments = params;
                if (null != arguments){
                    commandLine.addAll(arguments);
                }
                
                process = Runtime.getRuntime().exec(commandLine.toArray(new String[0]));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                
                String line;
                while ((line = bufferedReader.readLine()) != null){ 
                    //log.append(line);
                    //log.append("\n");
					
					Bundle data = new Bundle();
					data.putString("LINE", line);
					
					Message msg = logHandler.obtainMessage();
					msg.setData(data);
					msg.sendToTarget();									
										
					if(isCancelled())
						break;
                }
            } 
            catch (IOException e){
                error("CollectLogTask.doInBackground failed.\n"+ e.getMessage());//$NON-NLS-1$
            } 
			finally{
				if(process != null) process.destroy();
			}
        }
    }
    
       
    public void cancelCollectTask(){
        if (mCollectLogTask != null && !mCollectLogTask.isCancelled()) 
        {
            mCollectLogTask.cancel();
			Log.v(TAG, "Attempting to cancel"); // this also ensures the thread will have something to process
			try
			{
				mCollectLogTask.join(100);
				Log.d(TAG, "collect cancelled");
			}
			catch (InterruptedException e)
			{
				Log.e(TAG, "Collect log did not end successfully");
			}
            mCollectLogTask = null;
        }
    }
    
}
