package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;

@SuppressLint("UseSparseArrays")
public class Classifier {
	
	private ArrayList<Card> cards;
	private Card bestCard;
	private HandType type;
	private HashMap<Integer, Integer> counts;
	
	/**
	 * @param hand the hand which will be classified. Make sure you sorted
	 * the hand beforehand!
	 * @throws IllegalArgumentException if hand has more than 5 cards
	 */
	public Classifier(Hand hand) throws IllegalArgumentException{
		// Make sure we have 5 cards.
		if (hand.getSize() != 5) {
			throw new IllegalArgumentException("We need 5 cards in order " +
					"to do categorization");
		}
		cards = new ArrayList<Card>(hand.getCards());
		counts = new HashMap<Integer, Integer>();
		bestCard = null;
		
		// classify the hand
		classify();
	}

	/**
	 * @return the high card according to the hand type
	 */
	public Card getBestCard() {
		return this.bestCard;
	}
	
	/**
	 * @return the hand type
	 */
	public HandType getHandType() {
		return this.type;
	}
	
	/**
	 * Decide the type and highCard of the given cards.
	 * If it is better than this.type and/or this.highCard, update this
	 * accordingly. 
	 * 
	 * NOTE: Do not forget to initialize both type and bestCard!
	 */
	public void classify() {
		createCountMap();
		
		boolean straight = false;
		boolean flush = false;
		Card flushCard = null;
		Card straightCard = null;
		Card quadCard = null;
		
		if ((flushCard = getFlush()) != null) {
			flush = true;
			bestCard = flushCard;
		}
		if ((straightCard = getStraight()) != null) {
			straight = true;
			if (flush) {
				type = new HandType(HandType.FLUSH_ROYALE);
				return;
			}
		}
		
		if ((quadCard = getQuad()) != null) {
			type = new HandType(HandType.QUAD);
			bestCard = quadCard;
			return;
		}
		
		if ((bestCard = getFullHouse()) != null) {
			type = new HandType(HandType.FULL_HOUSE);
			return;
		}
		
		if (flush) {
			type = new HandType(HandType.FLUSH);
			bestCard = flushCard;
			return;
		}
		
		if (straight) {
			type = new HandType(HandType.STRAIGHT);
			bestCard = straightCard;
			return;
		}
		
		if ((bestCard = getThreeOfAKind()) != null) {
			type = new HandType(HandType.THREE_OF_A_KIND);
			return;
		}
		
		if ((bestCard = getTwoPair()) != null) {
			type = new HandType(HandType.TWO_PAIR);
			return;
		}
		
		if ((bestCard = getPair()) != null) {
			type = new HandType(HandType.PAIR);
			return;
		}
		
		if ((bestCard = getHighCard()) != null) {
			type = new HandType(HandType.HIGH_CARD);
			return;
		}
	}
	
	/**
	 * @return the highCard if cards are in order. Null otherwise
	 */
	public Card getStraight() {
		int endIndex = 4;
		boolean haveAce = false;
		// if we have an Ace
		if (cards.get(4).getValue() == 1) {
			endIndex = 3;
			haveAce = true;
		}
		for (int i = 0; i < endIndex; i++) {
			if (cards.get(i).getValue() - cards.get(i+1).getValue() != -1) {
				return null;
			}
		}
		// if we do not have an Ace, biggest card is the 4th one
		if (!haveAce) {
			return cards.get(4);
		}
		// if we have an Ace and the first card is 2
		else if (cards.get(0).getValue() == 2) {
			return cards.get(3);
		}
		// if we have an Ace and the first card is 10
		else if (cards.get(0).getValue() == 10) {
			return cards.get(4);
		}
		return null;
	}
	
	/**
	 * @return the highCard if there is a flush. Null otherwise
	 */
	public Card getFlush() {
		int type = cards.get(0).getSuit().ordinal();
		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(i).getSuit().ordinal() != type) {
				return null;
			}
		}
		return cards.get(4);
	}
	
	/**
	 * @return the quad card if there is one. null otherwise
	 */
	public Card getQuad() {
		if (counts.size() == 2) {
			for (Card c : cards) {
				if (getCount(c) == 4) {
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * @return the pair card if there is one. null otherwise
	 */
	public Card getPair() {
		if (counts.size() == 4) {
			for (Card c : cards) {
				if (getCount(c) == 2) {
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * @return the highest two pair card if there is, null otherwise
	 */
	public Card getTwoPair() {
		Card first = null;
		if (counts.size() != 3) {
			return null;
		}
		for (Card c : cards) {
			if (getCount(c) != 2) {
				continue;
			}
			if (first == null) {
				first = c;
			}
			else if (first.getValue() == c.getValue()) {
				continue;
			}
			else if (first.compareTo(c) > 0) {
				return first;
			}
			else {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * @return card which is three of a kind
	 */
	public Card getThreeOfAKind() {
		if (counts.size() == 3) {
			for (Card c : cards) {
				if (getCount(c) == 3) {
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * @return full house if there is one
	 */
	public Card getFullHouse() {
		if (counts.size() != 2) {
			return null;
		}
		for (Card c : cards) {
			if (getCount(c) == 3) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * @return the biggest card from cards
	 */
	public Card getHighCard() {
		return cards.get(4);
	}
	
	/**
	 * @param card the card whose count you want
	 * @return the count of the given card
	 */
	public int getCount(Card c) {
		return counts.get(c.getValue());
	}
	
	/**
	 * Create counts map for each card. Map will store # of times each card
	 * is in the hand.
	 */
	private void createCountMap() {
		int key;
		for (int i = 0; i < cards.size(); i++) {
			key = cards.get(i).getValue();
			if (counts.containsKey(key)) {
				counts.put(key, counts.get(key)+1);
			}
			else {
				counts.put(key, 1);
			}
		}
	}
}
