package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;

/**
 * Classifier for the given Hand. It will decide the type of the hand
 * and order the cards according the hand type. You can also compare
 * two classifier which will basically used to compare two Hands
 * 
 * @author ekinoguz
 */

@SuppressLint("UseSparseArrays")
public class Classifier implements Comparable<Classifier>{
  
  /** holds the cards from given hand */
  private ArrayList<Card> cards;
  
  /** ordered cards according to the classification */
  private ArrayList<Card> orderedCards;
  
  /** type of the hand after classification */
  private HandType type;
  
  /** return index from orderedCards ArrayList */
  private int bestCardIndex;
  
  /** stores the count of each card for the given Hand */
  private HashMap<Integer, Integer> counts;
  
  /**
   * @param hand the hand which will be classified. Make sure you sorted (
   * in decreasing order) the hand beforehand!
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
    orderedCards = new ArrayList<Card>();
    initBestCardIndex();
    
    // classify the hand
    classify();
  }

  /**
   * @return the next best card from orderedCards
   * @throw IndexOutOfBoundsException if there is no more cards to return
   */
  public Card getBestCard() throws IndexOutOfBoundsException {
    if (bestCardIndex < this.orderedCards.size()) {
      return this.orderedCards.get(bestCardIndex++);
    } else {
      throw new IndexOutOfBoundsException("Have no more best cards in Classifier");
    }
  }
  
  /**
   * @return the hand type
   */
  public HandType getHandType() {
    return this.type;
  }
  
  /**
   * Decide the type and bestCard of the given cards.
   * Note that both type and bestCard have to be initialized after 
   * this function
   */
  private void classify() {
    createCountMap();
    
    boolean straight = isStraight();
    ArrayList<Card> str = new ArrayList<Card>(orderedCards);
    boolean flush = isFlush();
    ArrayList<Card> flh = new ArrayList<Card>(orderedCards);
    
    if (straight && flush) {
      type = new HandType(HandType.FLUSH_ROYALE);
      return;
    }
    
    if (isQuad()) {
      type = new HandType(HandType.QUAD);
      return;
    }
    
    if (isFullHouse()) {
      type = new HandType(HandType.FULL_HOUSE);
      return;
    }
    
    if (flush) {
      type = new HandType(HandType.FLUSH);
      orderedCards = new ArrayList<Card>(flh);
      return;
    }
    
    if (straight) {
      type = new HandType(HandType.STRAIGHT);
      orderedCards = new ArrayList<Card>(str);
      return;
    }
    
    if (isSet()) {
      type = new HandType(HandType.THREE_OF_A_KIND);
      return;
    }
    
    if (isTwoPair()) {
      type = new HandType(HandType.TWO_PAIR);
      return;
    }
    
    if (isPair()) {
      type = new HandType(HandType.PAIR);
      return;
    }
    
    if (isHighCard()) {
      type = new HandType(HandType.HIGH_CARD);
      return;
    }
  }
  
  /**
   * @return true if there is straight, false otherwise
   */
  private boolean isStraight() {
    int startIndex = 0;
    boolean haveAce = false;
    // if we have an Ace
    if (cards.get(0).getValue() == 1) {
      startIndex = 1;
      haveAce = true;
    }
    for (int i = startIndex; i < cards.size()-1; i++) {
      if (cards.get(i).getValue() - cards.get(i+1).getValue() != 1) {
        return false;
      }
    }
    // if we do not have an Ace OR
    // if we have an Ace and the last card is either 10
    // if we have an Ace and the last card is either 2
    if (!haveAce || cards.get(4).getValue() == 10 || cards.get(4).getValue() == 2) {
      orderedCards = new ArrayList<Card>(cards);
      // ace is the smallest card if we have 5 high straight
      if (cards.get(4).getValue() == 2) {
        orderedCards.add(orderedCards.remove(0));
      }
      return true;
    }
    return false;
  }
  
  /**
   * @return true if there is a flush
   */
  private boolean isFlush() {
    int type = cards.get(0).getSuit().ordinal();
    for (int i = 1; i < cards.size(); i++) {
      if (cards.get(i).getSuit().ordinal() != type) {
        return false;
      }
    }
    orderedCards = new ArrayList<Card>(cards);
    return true;
  }
  
  /**
   * @return true if there is a quad, false otherwise
   */
  private boolean isQuad() {
    if (counts.size() != 2) {
      return false;
    }
    setUpOrderedCards(2);
    for (Card c : cards) {
      if (getCount(c) == 4) {
        orderedCards.set(0, c);
      } else if (getCount(c) == 1){
        orderedCards.set(1, c);
      } else {
        return false;
      }
    }
    return true;
  }
  
  /**
   * @return true if there is a pair, false otherwise
   */
  private boolean isPair() {
    if (counts.size() != 4) {
      return false;
    }
    setUpOrderedCards(4);
    int i = 1;
    for (Card c : cards) {
      if (getCount(c) == 2) {
        orderedCards.set(0, c);
      } else {
        orderedCards.set(i++, c);
      }
    }
    return true;
  }
  
  /**
   * @return true if there is a two pair, false otherwise
   */
  private boolean isTwoPair() {
    if (counts.size() != 3) {
      return false;
    }
    Card first = null;
    Card second = null;
    setUpOrderedCards(3);
    for (int i = 0; i < cards.size(); i++) {
      if (getCount(cards.get(i)) == 1) {
        orderedCards.set(2, cards.get(i));
      } else if (getCount(cards.get(i)) == 2) {
        if (first == null) {
          first = cards.get(i++);
        } else {
          second = cards.get(i++);
        }
      } else {
        return false;
      }
    }
    if (first.compareTo(second) < 0) {
      orderedCards.set(0, first);
      orderedCards.set(1, second);
    } else {
      orderedCards.set(0, second);
      orderedCards.set(1, first);
    }
    return true;
  }
  
  /**
   * @return true if three is a three of a kind
   */
  private boolean isSet() {
    if (counts.size() != 3) {
      return false;
    }
    setUpOrderedCards(1);
    for (Card c : cards) {
      if (getCount(c) == 3) {
        orderedCards.set(0, c);
      } else if (getCount(c) == 1) {
        orderedCards.add(c);
      } else {
        return false;
      }
    }
    // adjust second and third cards
    if (orderedCards.get(1).compareTo(orderedCards.get(2)) > 0) {
      orderedCards.add(1, orderedCards.get(2));
      orderedCards.remove(3);
    }
    return true;
  }
  
  /**
   * @return true if there is a full house
   */
  private boolean isFullHouse() {
    if (counts.size() != 2) {
      return false;
    }
    setUpOrderedCards(2);
    for (Card card : cards) {
      if (counts.get(card.getValue()) == 2) {
        orderedCards.set(1, card);
      } else if (counts.get(card.getValue()) == 3) {
        orderedCards.set(0, card);
      } else {
        return false;
      }
    }
    return true;
  }
  
  /**
   * @return true...
   */
  private boolean isHighCard() {
    orderedCards = new ArrayList<Card>(cards);
    return true;
  }
  
  /**
   * @param card the card whose count you want
   * @return the count of the given card
   */
  public int getCount(Card c) {
    return counts.get(c.getValue());
  }
  
  /**
   * Initialize the orderedCards ArrayList with given size of null cards
   * @param size the size of the orderedCards
   */
  private void setUpOrderedCards(int size) {
    orderedCards.clear();
    for (int i = 0; i < size; i++) {
      orderedCards.add(null);
    }
  }

  /**
   * @param another the another Classifier which we will compare
   * @return positive if this Classifier returns better (HandType, 
   * orderedCards) than the another. -1 if vice versa, 0 if they are equal
   */
  @Override
  public int compareTo(Classifier another) {
    int typeCompare = this.getHandType().compareTo(another.getHandType());
    if (typeCompare != 0) {
      return typeCompare;
    }
    int comp;
    for (int i = 0; i < this.getOrderedCardsSize(); i++) {
      comp = this.getBestCard().compareTo(another.getBestCard());
      if (comp != 0) {
        // do not forget to initBestCardIndex for next comparison
        another.initBestCardIndex();
        this.initBestCardIndex();
        return comp * -1;
      }
    }
    // do not forget to initBestCardIndex for next comparison
    another.initBestCardIndex();
    this.initBestCardIndex();
    return 0;
  }
  
  /**
   * @return string representation of Classifier, HandType + orderedCards
   */
  @Override
  public String toString() {
    return this.type.getMessage() + " with " + orderedCards; 
  }
  
  /**
   * Initializes best card index to starting point
   */
  private void initBestCardIndex() {
    this.bestCardIndex = 0;
  }
  
  /**
   * @return the size of orderedCards
   */
  private int getOrderedCardsSize() {
    return orderedCards.size();
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
      } else {
        counts.put(key, 1);
      }
    }
  }
}
