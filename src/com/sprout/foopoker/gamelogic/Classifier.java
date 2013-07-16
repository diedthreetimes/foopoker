package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.sprout.foopoker.gamelogic.Card.Suit;

import android.annotation.SuppressLint;

/**
 * Classifier for the given Hand. It will decide the type of the hand
 * and order the cards according the hand type. You can also compare
 * two classifier which will basically used to compare two Hands
 * 
 * @author ekinoguz
 */

// FIXME: There is a difference in semantics between the various isXXX() methods. 
//   Some are exclusive implying that if true they are the only HandType (e.g. isPair). Some are not (e.g. isStraight)
//   This is not a huge issue, but confuses testing.

@SuppressLint("UseSparseArrays")
public class Classifier implements Comparable<Classifier>{
  
  /** holds the cards from given hand */
  private ArrayList<Card> cards;
  
  /** ordered cards that make the hand according to the classification */
  private ArrayList<Card> orderedCards;
  
  /** type of the hand after classification */
  private HandType type;
  
  /** return index from orderedCards ArrayList */
  private int bestCardIndex;
  
  /** stores the count of each card for the given Hand */
  private HashMap<Integer, Integer> counts;
  
  /**
   * @param hand the hand which will be classified.
   * @throws IllegalArgumentException if hand has more than 5 cards
   */
  public Classifier(Hand hand) throws IllegalArgumentException{
    // Make sure we have at least 5 cards.
    if (hand.getSize() < 5) {
      throw new IllegalArgumentException("We need at least 5 cards in order " +
          "to do categorization");
    }
    
    cards = new ArrayList<Card>(hand.getCards());
    //Collections.sort(cards, Collections.reverseOrder());
    Collections.sort(cards);

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
  public Card nextBestCard() throws IndexOutOfBoundsException {
      return this.orderedCards.get(bestCardIndex++);
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
        
    if (isStraightFlush()) {
      type = new HandType(HandType.STRAIGHT_FLUSH);
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
    
    if (isFlush()) {
      type = new HandType(HandType.FLUSH);
      return;
    }
    
    if (isStraight()) {
      type = new HandType(HandType.STRAIGHT);
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
   * @return true if there is a straight flush, false otherwise
   */
  // This method is required because we we may have a straight flush,
  //      in which the same five cards are not the highest straight nor flush
  private boolean isStraightFlush() {
    // OPTIMIZE: See comment below, faster ways to find straights (and also check if each is a flush)
    
    // This is a tricky case, since here we could have multiple straights
    // Rather than repeat code, we spend some extra cycles and let isFlush find the flush for us.
    if (!isFlush())
      return false;
    
    Suit suit = orderedCards.get(0).getSuit();
    
    ArrayList<Card> only_suited_cards = new ArrayList<Card>();
    for (Card c : cards)
      if (c.getSuit() == suit)
        only_suited_cards.add(c);
    
    
    for (int i = only_suited_cards.size()-5; i >= 0; i--) {
      if (isStraightFrom(i, only_suited_cards)) {
          return true;
      }
    }
    
    return false;
  }

  /**
   * @return true if there is straight, false otherwise
   */
  private boolean isStraight() {
    // OPTIMIZE: This isn't the most efficient way of doing straight checking, since we repeat work
    for (int i = cards.size()-5; i >= 0; i--) {
      if (isStraightFrom(i, cards))
        return true;
      
    }
    return false;
  }
  
  // FIXME: Only the highest card in a straight matters
  private boolean isStraightFrom(int idx, ArrayList<Card> cards) {
    int startIndex = idx;
    boolean haveAce = false;
    // if we have an Ace
    if (cards.get(idx).getValue() == 1) {
      startIndex = idx+1;
      haveAce = true;
    }
    
    int in_a_row = haveAce ? 2 : 1;
    for (int i = startIndex; i < cards.size() - 1; i++) {
      int difference = cards.get(i).getValue() - cards.get(i+1).getValue();
      
      if (difference == 0)
        continue;
      if (difference != 1)
        break;
      else
        in_a_row += 1; 
    }
    
    if (in_a_row != 5)
      return false;
        
    // if we do not have an Ace OR 
    // if the last card is either 10 or 2 (this implies we have an ace)
    int lastValue = cards.get(idx+4).getValue();
    if (!haveAce || lastValue == 10 || lastValue == 2) {
      orderedCards.clear();
      int i = 0;
      Card cur = null;
      in_a_row = 0;
      while (in_a_row < 5) {
        if(cur == null || cards.get(idx+i).compareTo(cur) != 0) {
          cur = cards.get(idx+i);
          orderedCards.add(cur);
          in_a_row++;
        }
        
        i++;
      }
      // ace is the smallest card if we have 5 high straight
      if (lastValue == 2) {
        orderedCards.add(orderedCards.remove(0));
      }
      return true;
    }
    
    return false;
  }
  
  /**
   * @return true if there is a flush
   */
  private boolean isFlush() { // May not work properly for hands > 9 (I know of no such games)
    int [] suits = new int [4];
    
    for (Card c : cards) {
      suits[c.getSuit().ordinal()] += 1;
    }
    
    int suit = -1;
    for (int i = 0; i < 4; i++)
      if (suits[i] >= 5)
        suit = i;
    
    if (suit < 0)
      return false;

    // Add the five highest cards to orderedCards
    orderedCards.clear();
    for (Card c : cards)
      if (c.getSuit().ordinal() == suit && orderedCards.size() < 5)
        orderedCards.add(c);
    
    return true;
  }
  
  /**
   * @return true if there is a quad, false otherwise
   */
  private boolean isQuad() {
    orderedCards.clear();
    for (Card c : cards) {
      if (getCount(c) == 4) {
        orderedCards.add(c);
        break;
      } 
    }
   
    if (orderedCards.isEmpty())
      return false;
    
    int i = 0;
    while(orderedCards.get(0).compareTo(cards.get(i)) == 0)
      i++;
        
    // An out of bounds i signifies all cards are equal...
    orderedCards.add(cards.get(i));
      
    return true;
  }
  
  /**
   * @return true if there is a pair, false otherwise
   */
  private boolean isPair() {
    if (counts.size() != cards.size() - 1)
      return false;
    
    orderedCards.clear();
    orderedCards.add(null);
    
    for (Card c : cards) {
      if (getCount(c) == 2 && orderedCards.get(0) == null) {
        orderedCards.set(0, c);
      } else if (orderedCards.size() < 4 && (orderedCards.get(0) == null || orderedCards.get(0).compareTo(c) != 0)) {
        orderedCards.add(c);
      }
    }
    
    return (orderedCards.get(0) != null);
  }
  
  /**
   * @return true if there is a two pair, false otherwise
   */
  private boolean isTwoPair() {
    setUpOrderedCards(2);
    for (Card c : cards) {
      int count = getCount(c);
      if (count == 1) {
        if(orderedCards.size() < 3)
          orderedCards.add(c);
      } else if (count == 2) {
          if (orderedCards.get(0) == null)
            orderedCards.set(0,  c);
          else if (orderedCards.get(1) == null && orderedCards.get(0).compareTo(c) != 0)
            orderedCards.set(1, c);
          // The third pair could be the biggest kicker so we can't rely on count
          else if (orderedCards.size() < 3 && orderedCards.get(0).compareTo(c) != 0 && orderedCards.get(1).compareTo(c) != 0) 
            orderedCards.add(c);
      }
    }
    
    return (orderedCards.get(0) != null && orderedCards.get(1) != null);
  }
  
  /**
   * @return true if three is a three of a kind
   */
  private boolean isSet() {
    setUpOrderedCards(1);
    for (Card c : cards) {
      if (getCount(c) == 3 && orderedCards.get(0) == null) {
        orderedCards.set(0, c);
      } else if (getCount(c) == 1) {
        if (orderedCards.size() < 3 && (orderedCards.get(0) == null || c.compareTo(orderedCards.get(0)) != 0) )
          orderedCards.add(c);
      }
    }
    
    return (orderedCards.size() == 3 && orderedCards.get(0) != null);
  }
  
  /**
   * @return true if there is a full house
   */
  private boolean isFullHouse() {
    setUpOrderedCards(2);
    for (Card card : cards) {
      int count = getCount(card);
      
      if (count == 3 && orderedCards.get(0) == null)
        orderedCards.set(0,card);
      else if ((orderedCards.get(0) == null || card.compareTo(orderedCards.get(0)) != 0) && counts.get(card.getValue()) >= 2 && orderedCards.get(1) == null)
        orderedCards.set(1, card);
    }
    
    if (orderedCards.get(0) == null || orderedCards.get(1) == null)
      return false;
    return true;
  }
  
  /**
   * @return true but sets orderedCards appropriately
   */
  private boolean isHighCard() {
    orderedCards.clear();
    for (int i = 0; i < 5; i++)
      orderedCards.add(cards.get(i));
    
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
      comp = this.nextBestCard().compareTo(another.nextBestCard());
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
