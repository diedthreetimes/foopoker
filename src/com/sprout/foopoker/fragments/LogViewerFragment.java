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


public class LogViewerFragment extends ListFragment
{
	int mCurCheckPosition = 0;
	
	private static String TAG = "LogViewerFragment";
	private static boolean D = true;

	private LogViewerFragment.CollectLogTask mCollectLogTask = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Create the list adapter
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1));
		
		if (savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
		clearLog();
		watchLog();
	}
	
	private void log(String str){
		if(D) Log.d(TAG, str);
		addItem("LOG: " + str);
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
	
	public void clearLog(){
		// Clear the UI
		((ArrayAdapter<String>) getListAdapter()).clear();
		
		//TODO: The below doesn't seem to actually be clearing the log
		// Clear the file (although we don't bother doing this for now.
		Process process = null;
		try
		{
			String[] command = {"logcat", "-c"};
			process = Runtime.getRuntime().exec(command);
		}
		catch (IOException e)
		{
			log(e.getMessage());
		}
		finally {
			if(process != null) process.destroy();
		}
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

        mCollectLogTask = (CollectLogTask) new CollectLogTask().execute(list);
		log("Task Started.");
	}
	
    
	// To continuously watch the log this may need to be a service
    private class CollectLogTask extends AsyncTask<ArrayList<String>, Void, StringBuilder>
	{

		private static final int MAX_LOG_MESSAGE_LENGTH = 500;
        @Override
        protected void onPreExecute(){
            //showProgressDialog(getString(R.string.acquiring_log_progress_dialog_message));
        }
        
        @Override
        protected StringBuilder doInBackground(ArrayList<String>... params){
            final StringBuilder log = new StringBuilder();
			Process process = null;
            try{
                ArrayList<String> commandLine = new ArrayList<String>();
                commandLine.add("logcat");//$NON-NLS-1$
				
                ArrayList<String> arguments = ((params != null) && (params.length > 0)) ? params[0] : null;
                if (null != arguments){
                    commandLine.addAll(arguments);
                }
                
                process = Runtime.getRuntime().exec(commandLine.toArray(new String[0]));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                
                String line;
                while ((line = bufferedReader.readLine()) != null){ 
                    log.append(line);
                    log.append("\n");
					
					// Could potentially use a callback here.
					addItem(line);
					
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

            return log;
        }

        @Override
        protected void onPostExecute(StringBuilder log){
            if (null != log){
                //truncate if necessary
                int keepOffset = Math.max(log.length() - MAX_LOG_MESSAGE_LENGTH, 0);
                if (keepOffset > 0){
                    log.delete(0, keepOffset);
                }
                
                /*if (mAddiitonalInfo != null){
                    log.insert(0, App.LINE_SEPARATOR);
                    log.insert(0, mAddiitonalInfo);
                }*/
                
				// Start result activity and finish
                //finish();
            }
            else{
                //dismissProgressDialog();
				error("Failed to get Log");
                //showErrorDialog(getString(R.string.failed_to_get_log_message));
            }
			
			log("Task finished.");
        }
    }
    
    /*void showProgressDialog(String message){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
            public void onCancel(DialogInterface dialog){
                cancelCollectTask();
                finish();
            }
        });
        mProgressDialog.show();
    }
    
    private void dismissProgressDialog(){
        if (null != mProgressDialog && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }*/
    
    public void cancelCollectTask(){
        if (mCollectLogTask != null && mCollectLogTask.getStatus() == AsyncTask.Status.RUNNING) 
        {
            mCollectLogTask.cancel(true);
            mCollectLogTask = null;
        }
    }
    
}
