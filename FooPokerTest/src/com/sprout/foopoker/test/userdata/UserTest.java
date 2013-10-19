package com.sprout.foopoker.test.userdata;

import com.sprout.foopoker.userdata.User;
import com.sprout.foopoker.userdata.UserDatabaseHandler;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

// TODO: Should probably use ProviderTestCase2<ContentProvider>
//     This requires using a ContentProvider (which abstracts storage location)
public class UserTest extends AndroidTestCase{

	private UserDatabaseHandler handler;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
	  
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "test_");
		handler = new UserDatabaseHandler(context);
	}
	
	public void test_IsUserNull() {
		User user = new User();
		assertNotNull(user);
	}

	@Override
	public void tearDown() throws Exception {
	  super.tearDown();
		handler.close();
	}
	
}
