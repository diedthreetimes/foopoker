package com.sprout.foopoker.test.gamelogic;


import java.util.ArrayList;

import junit.framework.TestCase;

import com.sprout.foopoker.gamelogic.Card;
import com.sprout.foopoker.gamelogic.Hand;

public class HandTest extends TestCase {

  public void test_NotNull() {
    Hand hand = new Hand();
    assertNotNull(hand);
  }
  
  public void test_AddAndRetriveCorrectly() {
    Hand hand = new Hand();
    Card card = new Card(1);
    hand.appendCard(card);
    assertEquals(card, hand.getCard(0));
  }
  
  public void test_NotAddMoreThanCardSize() {
    Hand hand = new Hand();
    for (int i = 0; i < 7; i++) {
      Card c = new Card(i+1);
      hand.appendCard(c);
      assertEquals(hand.getCard(i), c);
    }
    try{
      hand.appendCard(new Card(39));
      assertTrue(false);
    } catch(Exception e) {
      assertTrue(true);
    }
  }

  public void test_SizeCheck() {
    Hand hand = new Hand();
    for (int i = 0; i < 7; i++) {
      hand.appendCard(new Card(i+1));
      assertEquals(hand.getSize(), (i+1));
    }
    for (int i = 0; i < 100; i++) {
      hand.appendCard(new Card(5));
      assertEquals(hand.getSize(), 7);
    }
    // FIXME: Where does the index out of bounds occur? Is this an appropriate exception?
  }
  
  public void test_AppendCards() {
    Hand hand = new Hand();
    ArrayList<Card> cards = new ArrayList<Card>();
    for (int i = 1; i <= 7; i++) {
      cards.add(new Card(i));
    }
    hand.appendCards(cards);
    assertEquals(hand.getSize(), 7);
    for (int i = 1; i <= 7; i++) {
      assertEquals(hand.getCard(i-1), new Card(i));
    }
    hand.appendCard(new Card(22)); // This is the line that throws the exception is that correct?
    hand.appendCards(cards); // If so why does this line exist?
    // FIXME: Where does the index out of bounds occur? Is this an appropriate exception?
  }
}
