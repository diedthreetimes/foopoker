package com.sprout.foopoker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LogViewerActivity extends Activity
{
	private static final String TAG = "LogActivity";
    private static final boolean D = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.logged_layout);
	}
		
}
