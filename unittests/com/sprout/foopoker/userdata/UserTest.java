package com.sprout.foopoker.userdata;

import org.junit.Test;

import android.test.ActivityTestCase;
import android.test.RenamingDelegatingContext;

public class UserTest extends ActivityTestCase{

	private UserDatabaseHandler handler;
	
	@Override
	public void setUp() {
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getActivity().getApplicationContext(), "test_");
		handler = new UserDatabaseHandler(context);
	}
	
	@Test
	public void test_IsUserNull() {
		System.out.println("ASD");
		User user = new User();
		assertNotNull(user);
	}

	@Override
	public void tearDown() throws Exception {
		handler.close();
		super.tearDown();
	}
	
}
