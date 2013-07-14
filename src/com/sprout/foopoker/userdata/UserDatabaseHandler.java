package com.sprout.foopoker.userdata;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite handler for User data which will be stored only in device.
 * 
 * @author ekinoguz
 *
 */
public class UserDatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
 
    // Contacts table name
    private static final String TABLE_USERS = "users";
    
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ALLOWS_HISTORY = "allow_history";
    private static final String WANTS_HISTORY = "wants_history";
    private static final String AVATAR_ID = "avatar_id";
    private static final String STACK = "stack";
	
	public UserDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
    // Create User Table
	@Override public void onCreate(SQLiteDatabase db) {
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
				KEY_ID + " INTEGER PRIMARY KEY," + 
				KEY_USERNAME + " TEXT UNIQUE," +
				PASSWORD + " TEXT," +
//				EMAIL + " TEXT," +
//				ALLOWS_HISTORY + " INTEGER," +
//				WANTS_HISTORY + " INTEGER," +
//				AVATAR_ID + " INTEGER," +
//				STACK + " INTEGER" + ")";
				")";
		db.execSQL(CREATE_USERS_TABLE);
	}

	@Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
 
        // Create tables again
        onCreate(db);
	}
	
	// Add a new User
	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, user.username);
		values.put(PASSWORD, user.getPassword());
		
		// insert the new user
		db.insert(TABLE_USERS, null, values);
		db.close();
	}
	
	/**
	 * @param id of the User who will be returned
	 * @return User whose id is given
	 */
	public User getUser(int id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	    Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
	            KEY_USERNAME, PASSWORD }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    User user = new User(Integer.parseInt(cursor.getString(0)),
	            cursor.getString(1), cursor.getString(2));
	    // return contact
	    return user;
	}
	
	
	/**
	 * We will not use this function, however I wrote it for testing SQLite
	 * @return List of all users
	 */
	public List<User> getAllUsers() {
	    List<User> contactList = new ArrayList<User>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_USERS;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            User user = new User();
	            user.id = Integer.parseInt(cursor.getString(0));
	            user.username = cursor.getString(1);
	            user.email = cursor.getString(2);
	            // Adding contact to list
	            contactList.add(user);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return contactList;
	}
}
