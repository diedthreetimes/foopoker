package com.sprout.foopoker.gamelogic;

import java.util.Arrays;
import java.util.Collections;

//TODO: We probably shoudln't have a signelton here. What if we want to support multiple games!
/**
 * Singleton design pattern since we should only have 1 dealer!
 * dealer can shuffle() and deal the top card from deck with dealCard()
 * 
 * @author ekinoguz
 */

public class Dealer {

  static Dealer dealer = null;
  
  // Properties
  protected Card[] cards;
  protected int position; // top of the deck
  
  private final int DECK_SIZE = 52;
  private final int SHUFFLE_COUNT = 6; // I want to shuffle the deck 6 times!
  
  /**
   * 
   * @return singleton dealer object
   */
  public static Dealer getInstance() {
    if (dealer == null) {
      dealer = new Dealer();
      return dealer;
    }
    return dealer;
  }
  
  // Constructor
  protected Dealer() {
    this.cards = new Card[DECK_SIZE];
    for (int i = 1; i <= DECK_SIZE; i++)
      cards[i-1] = new Card(i);
    this.position = 0;
  }
  
  /**
   * 
   * @return the top card from deck
   * @throws Exception
   */
  public Card dealCard() {
    if (position >= DECK_SIZE) {
      return null;
    }
    return cards[position++];
  }
  
  /**
   * shuffle the deck and initialize the deck
   */
  public void shuffle() {
    this.position = 0;
    for (int i = 1; i <= SHUFFLE_COUNT; i++)
      Collections.shuffle(Arrays.asList(cards));
  }
}
