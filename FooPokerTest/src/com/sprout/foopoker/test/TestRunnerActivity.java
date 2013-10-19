package com.sprout.foopoker.test;

import com.sprout.foopoker.LogViewerActivity;
import android.view.*;
import android.util.*;
import android.test.*;
import java.io.*;
import java.util.*;

public class TestRunnerActivity extends LogViewerActivity
{
	private static final String TAG = "TestRunnerActivity";
	private static final String RESULT_TAG = "Tests";
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.test_activity_actions, menu);
    	return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.action_test:
				runTests();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void runTests(){
		Log.d(TAG, "TESTS STARTED");
		
		// Should be able to run this strictly in java with something like below
		//   But is not being configured correctly. (May need to call onCreate manually)
		//InstrumentationTestRunner runner = new InstrumentationTestRunner();
		//runner.start();
		Process process = null;
		try{
			ArrayList<String> commandLine = new ArrayList<String>();
			commandLine.add("am");//$NON-NLS-1$
			commandLine.add("instrument");
			commandLine.add("-w");
			commandLine.add("--user");
			commandLine.add("0"); //todo: this isnt always 0
			commandLine.add("com.sprout.foopoker.test/android.test.InstrumentationTestRunner");
			
			process = Runtime.getRuntime().exec(commandLine.toArray(new String[0]));
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null){
				//logTest(line);
				// TODO: separte into function, and colorize based on failure/success
				Log.i(RESULT_TAG, line);
			}
			
			// Log.i(TAG, "Instrumentation errors");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = bufferedReader.readLine()) != null){
				Log.e(TAG, line);
			}
		} 
		catch (IOException e){
			Log.e(TAG,"testing failed.\n"+ e.getMessage());//$NON-NLS-1$
		} 
		finally{
			if(process != null) process.destroy();
		}
		
		
		Log.d(TAG, "TESTS COMPLETED");
	}
}
