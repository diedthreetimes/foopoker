package com.sprout.foopoker.gamelogic;


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

	public int id;
	public String username;
	public Avatar avatar;
	public long totalStack;
	
	boolean allowHistory;
	boolean wantHistory;
	
	// load previously created player
	public User(int id) {
		wantHistory = false;
		allowHistory = false;
		loadStack();
	}
	
	/**
	 * create new player. TODO: do not forget to save the player
	 * @param username
	 * @param avatarID
	 * @param totalStack
	 */
	public User(String username, int avatarID, long totalStack) {
		wantHistory = false;
		allowHistory = false;
		loadStack();
	}
	
	/**
	 * TODO: load stack somehow
	 */
	private void loadStack() {
		totalStack = 1000;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
