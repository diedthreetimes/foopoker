package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;


public class GameHand {

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
	
	private ArrayList<Card> fiveCards;
	
	public GameHand() {
		startTime = System.currentTimeMillis();
		dealer = Dealer.getInstance();
		fiveCards = new ArrayList<Card>();
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

	/**
	 * @return the cards of the game hand
	 */
	public ArrayList<Card> getCards() {
		return fiveCards;
	}
}
