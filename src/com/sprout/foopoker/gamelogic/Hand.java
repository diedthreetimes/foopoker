package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Represents a hand with any given number of cards (maximum of 7 cards).
 * 
 * @author ekinoguz
 */

public class Hand implements Comparable<Hand> {

  private ArrayList<Card> hand;
  private static final int CARD_SIZE = 7;
  
  /** Initialize a new Hand with nothing */
  public Hand() {
    this.hand = new ArrayList<Card>();
  }
  
  /**
   * Initialize a new hand from many cards
   * @param cards the cards that make the hand
   */
  public Hand(Card... cards) {
    this();
    for (Card c: cards)
      appendCard(c);
  }
  
  /** 
   * Initialize a new hand from many cards (as strings)
   * @param cards the cards that make the hand (passed to the card constructor)
   * 
   * See {@link Card#Card(String) Card(String)}
   */
  // This constructor is a bit weird, but useful.
  public Hand(String... cards) {
    this();
    for (String c: cards)
      appendCard(new Card(c));
  }
  
  /**
   * Initialize a new hand from many cards
   * @param cards the cards that make the hand
   */
  public Hand(Collection<Card> cards) {
    this();
    for(Card c: cards)
      appendCard(c);
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
    return this.hand; //FIXME: Should this return a copy of the cards array?
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

  // This is a convenience method more than anything else.
  @Override
  public int compareTo(Hand another) {
    return (new Classifier(this)).compareTo(new Classifier(another));
  }
}
