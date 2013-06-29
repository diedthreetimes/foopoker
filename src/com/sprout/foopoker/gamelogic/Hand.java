package com.sprout.foopoker.gamelogic;


public class Hand {

	public int id;
	
	public long moveTimer;
	public long startTime; 
	public long stack;
	
	public long ante;
	public long smallBlind;
	public long bigBlind;
	
	public Dealer dealer;
	
	public enum Status { STARTED, PLAYING, FINISHED }
	public Status status;
	
	
	public Hand() {
		startTime = System.currentTimeMillis();
		dealer = Dealer.getInstance();
	}
	
	/**
	 * Deal new hand. Deal the card
	 */
	public void startNewHand() {
		status = Status.STARTED;
		dealer.shuffle();
	}
	
	public void play() {
		preflop();
		if (!potGood())
			System.out.println("asd");
			
		flop();
	}
	
	public void preflop() {
		
	}
	
	public void flop() {
		
	}
	
	public void turnOrRiver() {
		
	}
	
	public void notifyPlayer(int id) {
		
	}
	
	public void saveHand() {
		
	}
	
	public boolean moveExpired() {
		return true;
	}
	
	public boolean potGood() {
		return true;
	}
	
	public String toString() {
		return "";
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
