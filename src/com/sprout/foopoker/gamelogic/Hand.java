package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;


/**
 * Represents a hand with any given number of cards (maximum of 7 cards).
 * 
 * @author ekinoguz
 */

public class Hand {

  private ArrayList<Card> hand;
  private static final int CARD_SIZE = 7;
  
  /** Initialize a new Hand with nothing */
  public Hand() {
    this.hand = new ArrayList<Card>();
  }
  
  /**
   * @param newCard the newCard which will be added to hand
   * @throws ArrayIndexOutOfBoundsException if you are trying to add
   *   8'th card.
   */
  public void appendCard(Card newCard) throws ArrayIndexOutOfBoundsException{
    if (hand.size() == CARD_SIZE) {
      throw new ArrayIndexOutOfBoundsException();
    }
    this.hand.add(newCard);  
  }
  
  /**
   * @param newCards the newCards which will be added to the hand
   * @throws ArrayIndexOutOfBoundsException if there will be more than 7 cards
   *   after the addition
   */
  public void appendCards(ArrayList<Card> newCards) throws ArrayIndexOutOfBoundsException{
    if (hand.size()+newCards.size() > CARD_SIZE) {
      throw new ArrayIndexOutOfBoundsException();
    }
    this.hand.addAll(newCards);  
  }
  
  /**
   * @param index the index of the card which we want
   * @return the Card with the given index
   * @throws ArrayIndexOutOfBoundsException if index < 0 or index >= 7
   */
  public Card getCard(int index) throws ArrayIndexOutOfBoundsException{
    if (index < 0 || index >= CARD_SIZE) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return hand.get(index);
  }
  
  /**
   * @return the hand which is ArrayList<Card>
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
   * @return String representation of the hand
   */
  public String toString() {
    return hand.toString();
  }
}
