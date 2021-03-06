package com.sprout.foopoker.test.gamelogic;

import java.util.Arrays;

import junit.framework.TestCase;

import com.sprout.foopoker.gamelogic.Card;
import com.sprout.foopoker.gamelogic.Dealer;

public class DealerTest extends TestCase{

  public void test_Singleton() {
    Dealer dealer1 = Dealer.getInstance();
    Dealer dealer2 = Dealer.getInstance();
    assertEquals(dealer1, dealer2);
  }
  
  public void test_NotNull() {
    Dealer d1 = Dealer.getInstance();
    assertNotNull(d1);
  }
  
  public void test_CheckFullDeck() {
    Dealer d1 = Dealer.getInstance();
    d1.shuffle();
    int[] checkArray = new int[52];
    Arrays.fill(checkArray, 1);
    Card c = d1.dealCard();
    int i = 0;
    while (c != null) {
      checkArray[c.getArrayIndex()]--;
      assertEquals (checkArray[c.getArrayIndex()], 0);
      c = d1.dealCard();
      i += 1;
    }
    assertEquals(i, 52);
  }
  
  public void test_CheckEndOfDeck() {
    Dealer d1 = Dealer.getInstance();
    d1.shuffle();
    for (int i = 1; i <= 52; i++)
      assertNotNull(d1.dealCard());
    assertNull(d1.dealCard());
  }
}
