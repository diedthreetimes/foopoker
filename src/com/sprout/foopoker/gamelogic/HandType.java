package com.sprout.foopoker.gamelogic;

/**
 * HandType class which will represent the type of hand. Examples are
 * four of a kind, high card, straight, ...
 * 
 * @author ekinoguz
 */
public class HandType implements Comparable<HandType>{
  
  public int type;
  
  public static final int STRAIGHT_FLUSH = 10;
  public static final int QUAD = 9;
  public static final int FULL_HOUSE = 8;
  public static final int FLUSH = 7;
  public static final int STRAIGHT = 6;
  public static final int THREE_OF_A_KIND = 5; 
  public static final int TWO_PAIR = 4;
  public static final int PAIR = 3;
  public static final int HIGH_CARD = 2;
    
  /**
   * @param t the type. Use predefined constansts in this class
   * @throws IllegalArgumentException if given t is not one of the supported
   *   types
   */
  public HandType(int t) throws IllegalArgumentException{
    if (t < HIGH_CARD || t > STRAIGHT_FLUSH) {
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
    case STRAIGHT_FLUSH: return "Flush Royale";
    case QUAD: return "Four of a Kind";
    case FULL_HOUSE: return "Full House";
    case FLUSH: return "Flush";
    case STRAIGHT: return "Straight";
    case THREE_OF_A_KIND: return "Three of a Kind";
    case TWO_PAIR: return "Two Pair";
    case PAIR: return "Pair";
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
  
  /**
   * @param otherType the other type which will be compared with given type
   * @return true if handTypes are same, false otherwise
   */
  @Override
  public boolean equals(Object otherType) {
    return this.getType() == ((HandType)otherType).getType();
  }
  
  /**
   * @param another the type card which will be compared with given type
   * @return negative if another type is better than this type, positive 
   * if this type is better than another type, 0 if they are equal.
   */
  @Override
  public int compareTo(HandType another) {
    return this.getType() - another.getType();
  }
  
  public String toString() {
    return getMessage();
  }
}

  
