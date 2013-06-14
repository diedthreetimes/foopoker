package com.sprout.foopoker.gamelogic;

/*
 * Card
 * 
 * card numbers are between [1, 52]
 * SPADE = [1, 13]
 * HEART = [14, 26]
 * DIAMOND = [27, 39]
 * CLUB = [40, 52]
 * 
 * 1 = ACE
 * 11 = JACK
 * 12 = QUEEN
 * 13 = KIND
 */
public class Card implements Comparable<Card>{

	public int value;
	
	public enum Suit { SPADE, HEART, DIAMOND, CLUB }
	public enum Value { JACK, QUEEN, KING, ACE }
	
	/**
	 * 
	 * @param value should be in [1, 52]
	 */
	public Card(int value) {
		if (value >= 1 && value <= 52)
			this.value = value;
		else
			throw new IllegalArgumentException(value + " is not allowed for Card");
	}
	
	/**
	 * 
	 * @return suit of the card
	 * @throws Exception
	 */
	public Suit getSuit() {
		if (value <= 13)
			return Suit.SPADE;
		else if (value <= 26)
			return Suit.HEART;
		else if (value <= 39)
			return Suit.DIAMOND;
		else // suit <= 52
			return Suit.CLUB;
	}
	
	/**
	 * 
	 * @return face value of card
	 */
	public int getValue() {
		int out = value % 13;
		if (out == 0)
			return out + 13;
		return out;
	}
	
	/**
	 * 
	 * @return value-1, to solve array indexing problem
	 */
	public int getIndex() {
		return value-1;
	}
	
	@Override
	public int compareTo(Card another) {
		if (this.value < another.value)
			return -1;
		else if (this.value == another.value)
			return 0;
		else
			return 1;
	}
	
	@Override
	public String toString() {
		return getSuit() + "-" + getValue();
	}
	
	public static void main(String[] args) {
		Card c1 = new Card(13);
		assert (c1.getSuit() == Suit.SPADE);
		assert (c1.getValue() == 13);
		Card c2 = new Card(52);
		assert (c2.getSuit() == Suit.CLUB);
		assert (c2.getValue() == 13);
		Card c3 = new Card(27);
		assert (c3.getSuit() == Suit.DIAMOND);
		assert (c3.getValue() == 1);
	}

}
