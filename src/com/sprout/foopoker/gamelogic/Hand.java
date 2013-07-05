package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;


/**
 * Represents a hand with any given number of cards (maximum of 7 cards).
 * 
 * @author ekinoguz
 */

public class Hand {

	private ArrayList<Card> hand;
	
	private HandType type;
	private Card highCard;
	
	private final static int CARD_SIZE = 7;
	
	public Hand() {
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * 
	 * @param newCard the newCard which will be added to hand
	 * @throws ArrayIndexOutOfBoundsException if you are trying to add
	 * 	CARD_SIZE+1=8'th card.
	 */
	public void appendCard(Card newCard) throws ArrayIndexOutOfBoundsException{
		if (hand.size() == CARD_SIZE) {
			throw new ArrayIndexOutOfBoundsException();
		}
		this.hand.add(newCard);	
	}
	
	/**
	 * 
	 * @param index the index of the card which we want
	 * @return the Card with the given index
	 * @throws ArrayIndexOutOfBoundsException if index < 0 or index >= CARD_SIZE
	 */
	public Card getCard(int index) throws ArrayIndexOutOfBoundsException{
		if (index < 0 || index >= CARD_SIZE) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return hand.get(index);
	}
	
	/**
	 * @return hand which is ArrayList<Card>
	 */
	public ArrayList<Card> getCards() {
		return this.hand;
	}
	
	/**
	 * @return the size of the hand
	 */
	public int getSize() {
		return this.hand.size();
	}
	
	/**
	 * @return the type of the Hand
	 */
	public HandType getType() {
		return type;
	}
	
	/**
	 * @return the high card of hand
	 */
	public Card getHighCard() {
		return highCard;
	}
	
	/**
	 * @return String representation of the hand
	 */
	public String toString() {
		return hand.toString();
	}
	
	/**
	 * @return the Android-friendly message according to the type
	 */
	public String getMessage() {
		return type.getMessage() + " with " + highCard;
	}
}
