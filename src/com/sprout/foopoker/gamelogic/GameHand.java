package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;


public class GameHand {

	public int id;
    
    public long stack;
	
	public Blind blind;
	
	public Dealer dealer;
	public Table players;
	
    // What is the meaning of this ENUM
	public enum Status { STARTED, PLAYING, FINISHED }
	public Status status;
	
    // These are the shared table cards.
	private ArrayList<Card> fiveCards;
	
	public GameHand(Table players, Blind blind) {
		dealer = Dealer.getInstance();
		fiveCards = new ArrayList<Card>();
		this.players = players;
		this.blind = blind;
	}
	
	/**
	 * Deal new hand. Deal the card
	 */
	public void startNewHand() {
		status = Status.STARTED;
		dealer.shuffle();
	}
	
	public void play() {
	  // TODO: Post blinds and antes
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
	
	public boolean potGood() {
		return true;
	}
	
	public String toString() {
		return "";
	}
	
  /**
   * @return the players who win the game
   */
  public ArrayList<Player> getWinners() {
    ArrayList<Player> bestPlayers = new ArrayList<Player>();
    bestPlayers.add(players.get(0));
    if (players.size() == 1) {
      return bestPlayers;
    }
    int compareValue;
    for (int i = 1; i < players.size(); i++) {
      compareValue = bestPlayers.get(0).getHand().compareTo(players.get(i).getHand());
      if (compareValue == 0) {
        bestPlayers.add(players.get(i));
      } else if (compareValue < 0){
        bestPlayers.clear();
        bestPlayers.add(players.get(i));
      }
    }
    return bestPlayers;
  }

	/**
	 * @return the cards of the game hand
	 */
	public ArrayList<Card> getCards() {
		return fiveCards;
	}
}
