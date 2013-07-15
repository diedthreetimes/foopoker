package com.sprout.foopoker.test.gamelogic;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

import com.sprout.foopoker.gamelogic.Card;
import com.sprout.foopoker.gamelogic.Card.Suit;

public class CardTest extends TestCase {
  
  public void test_IsCardCreated() {
    Card c1 = new Card(13);
    assertSame(c1.getValue(), 13);
  }
  
//  (expected=IllegalArgumentException.class)
//  public void test_NegativeCard() {
//    new Card(-1);
//  }
  
  public void test_IsCardSpade() {
    Card c1 = new Card(13);
    assertSame(c1.getSuit(), Suit.SPADE);
  }
  
  public void test_IsCardHeart() {
    Card c3 = new Card(14);
    assertSame(c3.getSuit(), Suit.HEART);
  }
  
  public void test_IsCardClub() {
    Card c2 = new Card(52);
    assertSame(c2.getSuit(), Suit.CLUB);
  }
  
  public void test_IsCardDiamond() {
    Card c3 = new Card(27);
    assertSame(c3.getSuit(), Suit.DIAMOND);
  }
  
  public void test_IsCardCorrectValue() {
    Card c3 = new Card(27);
    assertSame(c3.getValue(), 1);
  }
  
  public void test_CompareBigger() {
    Card c1 = new Card(3);
    Card c2 = new Card(2);
    assertTrue(c1.compareTo(c2) < 0);
  }
  
  public void test_CompareAce1() {
    Card c1 = new Card(1);
    Card c2 = new Card(2);
    assertTrue(c1.compareTo(c2) < 0);
  }
  
  public void test_CompareAce2() {
    Card c1 = new Card(2);
    Card c2 = new Card(14);
    assertTrue(c1.compareTo(c2) > 0);
  }
  
  public void test_SortArrayList() {
    ArrayList<Card> list = new ArrayList<Card>();
    list.add(new Card(3));
    list.add(new Card(2));
    list.add(new Card(5));
    list.add(new Card(1));
    list.add(new Card(4));
    Collections.sort(list);
    assertEquals(list.get(0).getValue(), 1);
    assertEquals(list.get(1).getValue(), 5);
    assertEquals(list.get(2).getValue(), 4);
    assertEquals(list.get(3).getValue(), 3);
    assertEquals(list.get(4).getValue(), 2);
  }
  
  public void test_CompareEqual() {
    Card c1 = new Card(27);
    Card c2 = new Card(14);
    assertTrue(c1.compareTo(c2) == 0);
  }
  
  public void test_Equal() {
    Card c1 = new Card(1);
    Card c2 = new Card(14);
    assertTrue(c1.equals(c2) != true);
    
    c2 = new Card(1);
    assertTrue(c1.equals(c2) == true);
  }
  
  public void test_DifferentSuits() {
    Card c1 = new Card(1);
    Card c2 = new Card(16);
    Card c3 = new Card(29);
    Card c4 = new Card(45);
    assertEquals(c1.getSuit(), Suit.SPADE);
    assertEquals(c2.getSuit(), Suit.HEART);
    assertEquals(c3.getSuit(), Suit.DIAMOND);
    assertEquals(c4.getSuit(), Suit.CLUB);
  }
  
  public void test_Constructor() {
    Card c1 = new Card(Suit.DIAMOND, 7);
    assertEquals(c1.getSuit(), Suit.DIAMOND);
    assertEquals(c1.getValue(), 7);
    
    c1 = new Card(Suit.HEART, 13);
    assertEquals(c1.getSuit(), Suit.HEART);
    assertEquals(c1.getValue(), 13);
    
    Card c2 = new Card("Kh");
    assertEquals(c1,c2);
    
    c1 = new Card(Suit.CLUB, 1);
    assertEquals(c1.getSuit(), Suit.CLUB);
    
    c2 = new Card("Ac");
    assertEquals(c1,c2);
    
    c1 = new Card(Suit.SPADE, 3);
    assertEquals(c1.getSuit(), Suit.SPADE);
    assertEquals(c1.getValue(), 3);
    
    c2 = new Card("3s");
    assertEquals(c1,c2);
    
    c2 = new Card(3);
    assertEquals(c1,c2);
    
    c1 = new Card(Suit.HEART, 11);
    c2 = new Card("jh");
    assertEquals(c1,c2);
    
    c1 = new Card(Suit.CLUB, 13);
    c2 = new Card("KC");
    assertEquals(c1,c2);
    
    c1 = new Card(Suit.DIAMOND, 1);
    c2 = new Card("Ad");
    assertEquals(c1,c2);
    
    c1 = new Card("0d");
    assertEquals(c1.getValue(), 10);
    
    c1 = new Card("2d");
    assertEquals(c1.getValue(), 2);
    
    c1 = new Card("5H");
    assertEquals(c1.getValue(), 5);
    
    try{
      c1 = new Card("bs");
      fail("Expect to throw");
    } catch(IllegalArgumentException e){ }

    try{
      c1 = new Card("2g");
      fail("Expect to throw");
    } catch(IllegalArgumentException e){ }
    
    try{
      c1 = new Card("10s");
      fail("Expect to throw");
    } catch(IllegalArgumentException e){ }
    
  }
}
