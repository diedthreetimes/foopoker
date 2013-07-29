package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * CircularPlayers of Players which will be used in Hand
 */

public class Table {

	private ArrayList<Player> players;
	private int cur;
	
	private final int MAX_PLAYERS = 10;
	private Queue<Integer> freePositions;
	
	public Table() {
		players = new ArrayList<Player>(MAX_PLAYERS);
		freePositions = new LinkedList<Integer>();
		for (int i = 0; i < MAX_PLAYERS; i++) {
			players.add(null);
			freePositions.add(i);
		}
		cur = 0;
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
		while (players.get(cur) == null)
			cur = (cur + 1) % size();
		return players.get(cur);
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Player get(int i) {
		return players.get(i);
	}
	
	/**
	 * Advance current to next player
	 */
	public Player next() {
		cur += 1;
		cur %= size();
		return peek();
	}
	
	/**
	 * Removes the player who is currently playing
	 */
	public Player remove() {		
		return remove(cur);
	}
	
	/**
	 * Removes player at idx
	 */
	public Player remove(int idx) {
	   Player p = get(idx);
	   players.set(idx, null);
	   freePositions.add(idx);
	   return p;
	}
	
	/**
	 * Check the stack of each player in table. Kick players with 0 stack
	 */
	public void checkStacks() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null && players.get(i).stack == 0) {
				players.set(i, null);
				freePositions.add(i);
			}
		}
	}
	
	public int size() {
		return players.size();
	}
}
