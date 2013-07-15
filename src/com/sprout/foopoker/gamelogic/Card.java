package com.sprout.foopoker.gamelogic;

/**
 * Card
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
   * @param suit of the new card
   * @param value of the new card [1-13]
   */
  public Card(Suit suit, int value){
    if (value < 1 || value > 13){
      throw new IllegalArgumentException("Unsported value " + value);
    }
    
    this.value = value;
    switch (suit){
    case SPADE:
      break;
    case HEART:
      this.value += 13;
      break;
    case DIAMOND:
      this.value += 13*2;
      break;
    case CLUB:
      this.value += 13*3;
      break;
    }
  }
  
  /**
   * 
   * Convert the two character string "[value][suit]" to a Card. Here '0' represents 10.
   * 
   * @param string representation (case insensitive) of the card.
   *   Should be value ([2-9|0|A|K|Q|J]) followed by suit [s|h|c|d]
   * 
   */
  public Card(String string){
    
    switch(Character.toUpperCase( string.charAt(0) )){
    case 'A':
      this.value = 1;
      break;
    case 'K':
      this.value = 13;
      break;
    case 'Q':
      this.value = 12;
      break;
    case 'J':
      this.value = 11;
      break;
    case '0':
      this.value = 10;
      break;
    default:
        // 1 is actually accepted here as an ace.
      this.value = Character.getNumericValue((string.charAt(0)));  

      if(this.value < 0 || this.value > 10)
        throw new IllegalArgumentException("Invalid value " + string.charAt(0));
      
      break;
    }
    
    switch(Character.toUpperCase( string.charAt(1) )){
    case 'S': // Value is the same
      break;
    case 'H':
      this.value += 13;
      break;
    case 'D':
      this.value += 13*2;
      break;
    case 'C':
      this.value += 13*3;
      break;
    default:
      throw new IllegalArgumentException("Invalid suit " + string.charAt(1));
    }
   }
  
  /**
   * @param value of the card, should be in [1, 52]
   */
  public Card(int value) {
    if (value >= 1 && value <= 52) {
      this.value = value;
    } else {
      throw new IllegalArgumentException(value + " is not allowed for " +
          "Card");
    }
  }
  
  /**
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
  
  // TODO (ekin): this function should somehow use Value enum
  /**
   * @return face value of card
   */
  public int getValue() {
    int out = value % 13;
    if (out == 0) {
      return out + 13;
    }
    return out;
  }
  
  // TODO (ekin)
  /**
   * @return the logo of card
   */
  public String getLogo() {
    return "";
  }
  
  /** 
   * @return return arrayIndex which is value-1
   */
  public int getArrayIndex() {
    return value-1;
  }
  
  /**
   * @param another the another card which will be compared with given card
   * @return positive if another card is bigger than this card, negative 
   * if this card is bigger than another card, 0 if they are equal. Note
   * that 1 = Ace, bigger than all other cards
   */
  @Override
  public int compareTo(Card another) {
    int thisCard = this.getValue();
    int anotherCard = another.getValue();
    if (thisCard == anotherCard) {
      return 0;
    }
    
    if (thisCard == 1) {
      return -1;
    } else if (anotherCard == 1) {
      return +1;
    } else {
      return anotherCard - thisCard;
    }
  }
  
  /**
   * @param otherCard the other card which will be compared with given card
   * @return true if cards are same, false otherwise
   */
  @Override
  public boolean equals(Object otherCard) {
    if (otherCard == null) {
      return this == null ? true : false;
    }
    return this.getSuit().equals(((Card)otherCard).getSuit()) &&
        this.getValue() == ((Card)otherCard).getValue();
  }
  
  /**
   * @return string representation of Card
   */
  @Override
  public String toString() {
    return getValue() + "-" + getSuit();
  }
}
