package com.sprout.foopoker.test.userdata;

import com.sprout.foopoker.userdata.User;
import com.sprout.foopoker.userdata.UserDatabaseHandler;

import android.test.ActivityTestCase;
import android.test.RenamingDelegatingContext;

public class UserTest extends ActivityTestCase{

	private UserDatabaseHandler handler;
	
	@Override
	public void setUp() throws Exception {
	  super.setUp();
	  
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getActivity().getApplicationContext(), "test_");
		handler = new UserDatabaseHandler(context);
	}
	
	public void test_IsUserNull() {
		System.out.println("ASD");
		User user = new User();
		assertNotNull(user);
	}

	@Override
	public void tearDown() throws Exception {
	  super.tearDown();
		handler.close();
	}
	
}
