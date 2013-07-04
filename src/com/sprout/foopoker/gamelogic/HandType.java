package com.sprout.foopoker.gamelogic;

/**
 * HandType class which will represent the type of hand. Examples are
 * four of a kind, high card, straight, ...
 * 
 * @author ekinoguz
 */
public class HandType {
	
	public int type;
	
	public static final int FLUSH_ROYALE = 10;
	public static final int QUAD = 9;
	public static final int FULL_HOUSE = 8;
	public static final int FLUSH = 7;
	public static final int STAIGHT = 6;
	public static final int THREE_OF_A_KIND = 5; 
	public static final int TWO_PAIR = 4;
	public static final int HIGH_CARD = 3;
		
	/**
	 * @param t the type. Use predefined constansts in this class
	 * @throws IllegalArgumentException if given t is not one of the supported
	 * 	types
	 */
	public HandType(int t) throws IllegalArgumentException{
		if (t < 3 || t > 10) {
			throw new IllegalArgumentException();
		}
		this.type = t;
	}
	
	/**
	 * @return the message associated with the type
	 * We know that this will never return null 
	 */
	public String getMessage(){
		switch(type) {
		case FLUSH_ROYALE: return "Flush Royale";
		case QUAD: return "Four of a Kind";
		case FULL_HOUSE: return "Full House";
		case FLUSH: return "Flush";
		case STAIGHT: return "Straight";
		case THREE_OF_A_KIND: return "Three of a Kind";
		case TWO_PAIR: return "Two Pair";
		case HIGH_CARD: return "High Card";
		}
		return null;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return this.type;
	}
}
