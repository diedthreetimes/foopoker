package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;

import com.sprout.foopoker.userdata.User;

public class Player{

	
	public long stack;
	public Status status;
	public enum Status { PLAYING, FOLDED, FINISH }
	public User user;
	
	private Hand hand;
	
	public Player(int id, long stack) {
		this.user = new User(id);
		if (stack > 0 && stack <= user.totalStack) {
			this.stack = stack;
		}
		else {
			throw new IllegalArgumentException(stack + " is not legal");
		}
		this.hand = new Hand();
		this.status = Status.PLAYING;
	}
	
	public void bet(long value) {
		stack -= value;
	}
	
	public void winPot(long value) {
		stack += value;
	}
	
	/**
	 * 
	 * @return hand of the player. I do check for the status
	 */
	public ArrayList<Card> showCards() {
		if (status.equals(Status.FOLDED)) {
			if (user.allowsHistory)
				return hand.getCards();
			else
				return null;
		} 
		// TODO: we have to be sure that game is finished
		else if (status.equals(Status.FINISH)){
			return hand.getCards();
		}
		// TODO: I know this looks weird:)
		else if (status.equals(Status.PLAYING))
			return null;
		return null;
		
	}
	
	public void foldHand() {
		status = Status.FOLDED;
	}
	
	public void finishHand() {
		status = Status.FINISH;
	}
	
	// TODO: copy constructor
	
	public ArrayList<Card> getCards() {
		return hand.getCards();
	}
	
	public Hand getHand() {
	  return hand;
	}
	
	public Card getCard(int index) throws IndexOutOfBoundsException {
	  return hand.getCard(index);
	}
	
	public void appendCards(ArrayList<Card> newCards) {
		this.hand.appendCards(newCards);
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	/**
	 * @return id of the Player
	 */
	public int getId() {
		return user.id;
	}

}
