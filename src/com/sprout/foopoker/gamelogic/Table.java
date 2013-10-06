package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

/*
 * CircularPlayers of Players which will be used in Hand
 * 
 * Indexes in the public API are based on the players left.
 */

// TODO: This class should handle skipping the small blind after being removed!
// TODO: This calss should handle heads up scenario correctly. (Where the dealer is the small blind)
// TODO: This class shoudl be enumerable
public class Table {

	private ArrayList<Player> players;
	private int cur;
	private int dealer; // This is in the public index space (so blinds can be calculated easily)
	
	private final int MAX_PLAYERS = 10;
	private Queue<Integer> freePositions;
	
	private boolean[] foldedPlayers;
	
	
	
	public Table() {
		players = new ArrayList<Player>(MAX_PLAYERS);
		foldedPlayers = new boolean[MAX_PLAYERS];
		freePositions = new LinkedList<Integer>();
		for (int i = 0; i < MAX_PLAYERS; i++) {
			players.add(null);
			freePositions.add(i);
			foldedPlayers[i] = false;
		}
		cur = 0;
		
		dealer = 0;
	}
    
	/**
	 * 
	 * @param p
	 */
	public void insert(Player p) {
		int i = freePositions.poll();
		players.set(i, p);
	}
	
	/**
	 * 
	 * @return current player
	 * This call should be followed by next() to advance next player
	 */
	public Player peek() {
		return players.get(cur);
	}
	
	/**
	 * 
	 * @return the index of the dealer;
	 */
	public int getDealerIdx() {
	  return dealer;
	}
	
	public Player getDealer() {
	  return get(dealer);
	}
	
	public Player getSmallBlind() {
	  return get(dealer+1);
	}
	
	public Player getBigBlind() {
	  return get(dealer+2);
	}
	
	/**
	 * Move the button. This also initializes cur.
	 */
	public void advanceDealer() {
		
	  // Clear folded players
	  for(int i=0; i < foldedPlayers.length; i++)
	  	foldedPlayers[i] = false;
		
	  dealer++;
	  dealer %= size();
	  
	  cur = this.publicIdxToPlayersIdx(dealer+3); 
	}
	
	/**
	 * Advance to the next round of betting.
	 */
	public void nextRound() {
		cur = this.publicIdxToPlayersIdx(dealer+3);
	}
	
	/**
	 * Access into the circular list. 
	 * @param i the index
	 * @return the ith active player
	 */
	public Player get(int i) {
		return players.get(publicIdxToPlayersIdx(i));
	}
	
	/**
	 * @param i the index
	 * @return true if player i has folded
	 */
	// Exposing this is a bit hacky. Perhaps we should incorporate this logic into get.
	public boolean getFolded(int i) {
		return foldedPlayers[publicIdxToPlayersIdx(i)];
	}
	
	/**
	 * Advance current to next player
	 */
	public Player next() {
		cur += 1;
		cur %= size();
		
		// While we have an empty seat or a folded player
    	while (players.get(cur) == null || foldedPlayers[cur])
      		cur = (cur + 1) % size();
    
		return peek();
	}
	
	/**
	 * Removes the player who is currently playing
	 */
	public Player remove() {		
		return _remove(cur);
	}
	
	
	/**
	 * 
	 * @param idx of player to remove
	 * @return the deleted player
	 */
	public Player remove(int idx) {
	  return _remove(publicIdxToPlayersIdx(idx));
	}
	
	/**
	 * Fold the current player removing him from this round
	 */
	public void foldCurrent() {
		foldedPlayers[cur] = true; 
	}
	
	
	/**
	 * The number of players still playing the hand (non folded)
	 */
	public int numActive(){
		// TODO: This is not always be accuate (if we have an empty seat marked as folded)
		int num_folded = 0;
		for( int i=0; i < foldedPlayers.length; i++ )
			if(foldedPlayers[i])
				num_folded++;
		return size() - num_folded;
	}
	
	
	/**
	 * Removes player at idx
	 */
	private Player _remove(int idx) {
	   Player p = players.get(idx);
	   players.set(idx, null);
	   freePositions.add(idx);
	   return p;
	}
	
	/**
	 * Check the stack of each player in table. Kick players with 0 stack
	 */
	public void removeBroke() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null && players.get(i).stack == 0) {
				_remove(i);
			}
		}
	}
	
	/**
	 * @return The number of active players
	 */
	public int size() {
		return players.size() - freePositions.size();
	}
	
	// Converts the public indexes (indexes into active players) into indexes on the players list (which contains nulls)
	private int publicIdxToPlayersIdx(int idx) {
	  idx %= size();
	  int k=-1;
	  for (int i=0; i<players.size(); i++)
	    if(players.get(i) != null)
	      k++;
	      if(idx == k)
	        return k;	    
	      
	  return -1; // This should never happen
	}
}
