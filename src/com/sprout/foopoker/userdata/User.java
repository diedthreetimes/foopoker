package com.sprout.foopoker.userdata;



/**
 * 
 * @author ekinoguz
 *
 * Base user object
 * 
 * totalStack denotes her total stack, not in game stack.
 * Idea is she can enter to cash game with "arbitrary" stack.
 * Also, her stack in tournament will be assigned to her according to tournament
 * 	limits.
 */
public class User {

	// constructors
	public int id;
	public String username;
	private String password;
	public String email;
	public boolean allowsHistory;
	public boolean wantsHistory;
	public int avatarId;
	public long totalStack;
	
	
	public User() {
		
	}
	
	// load previously created player
	public User(int id) {
		this.id = id;
		loadOptions();
		loadStack();
	}
	
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * create new user
	 * @param username
	 * @param avatarID
	 * @param totalStack
	 */
	public User(String username, int avatarID, long totalStack) {
		wantsHistory = false;
		allowsHistory = false;
		savePlayer();
	}
	
	public void savePlayer() {
		saveOptions();
		saveStack();
	}
	
	/**
	 * TODO: (ekin)
	 * @return password hashed and/or salted
	 */
	public String getPassword() {
		return password;
	}
	
	// TODO: (ekin)
	private void loadStack() {
		totalStack = 1000;
	}
	
	// TODO: (ekin)
	private void loadOptions() {
		
	}
	
	private void saveOptions() {
		
	}
	
	private void saveStack() {
		
	}
}
