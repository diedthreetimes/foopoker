package com.sprout.foopoker.test.gamelogic;

import java.lang.reflect.Method;
import java.util.Collections;

import junit.framework.TestCase;

import com.sprout.foopoker.gamelogic.*;

public class ClassifierTest extends TestCase {
  
  /*
   * FIXME: In my opinion these hands deserve more descriptive names
   */
  public Hand h1;  // J - J - J - 10 - 10
  public Hand h2;  // J - J - J - J - 10
  public Hand h3;  // 1 - 5 - 4 - 3 - 2 straight different suits
  public Hand h4;  // A - K - Q - J - 10 straight different suits
  public Hand h5;  // J - 10 - 9 - 8 - 7 straight different suits
  public Hand h6;  // A - A - Q - 2 - 2
  public Hand h7;  // K - K - 7 - 6 - 5
  public Hand h8;  // A - A - A - 3 - 2
  public Hand h9;  // J - J - J - 10 - 9
  public Hand h10; // 10 - 8 - 6 - 5 - 2 for High Card
  public Hand h11; // A - 5 - 4 - 3 - 2 for flush royale
  public Hand h12; // A - K - Q - J - 10 for flush royale
  public Hand h13; // A - 9 - 5 - 3 - 2 for flush
  public Hand h14; // 9 - 5 - 4 - 3 - 2 for flush
  public Hand h15; // 3 - 3 - 2 - 2 - 2
  public Hand h16; // 10 - 3 - 3 - 2 - 2

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    // J - J - J - 10 - 10
    h1 = new Hand();
    h1.appendCard(new Card(37));
    h1.appendCard(new Card(24));
    h1.appendCard(new Card(11));
    h1.appendCard(new Card(10));
    h1.appendCard(new Card(23));
    
    // J - J - J - J - 10
    h2 = new Hand();
    h2.appendCard(new Card(50));
    h2.appendCard(new Card(37));
    h2.appendCard(new Card(24));
    h2.appendCard(new Card(11));
    h2.appendCard(new Card(23));
    
    // 1 - 5 - 4 - 3 - 2 straight different suits
    h3 = new Hand();
    h3.appendCard(new Card(1));
    h3.appendCard(new Card(31));
    h3.appendCard(new Card(30));
    h3.appendCard(new Card(16));
    h3.appendCard(new Card(15));
    
    // A - K - Q - J - 10 straight different suits
    h4 = new Hand();
    h4.appendCard(new Card(27));
    h4.appendCard(new Card(26));
    h4.appendCard(new Card(38));
    h4.appendCard(new Card(24));
    h4.appendCard(new Card(10));
    
    // J - 10 - 9 - 8 - 7 straight different suits
    h5 = new Hand();
    h5.appendCard(new Card(24));
    h5.appendCard(new Card(36));
    h5.appendCard(new Card(48));
    h5.appendCard(new Card(8));
    h5.appendCard(new Card(7));
    
    // A - A - Q - 2 - 2
    h6 = new Hand();
    h6.appendCard(new Card(1));
    h6.appendCard(new Card(27));
    h6.appendCard(new Card(38));
    h6.appendCard(new Card(2));
    h6.appendCard(new Card(15));
    
    // K - K - 7 - 6 - 5
    h7 = new Hand();
    h7.appendCard(new Card(13));
    h7.appendCard(new Card(26));
    h7.appendCard(new Card(7));
    h7.appendCard(new Card(6));
    h7.appendCard(new Card(5));
    
    // A - A - A - 3 - 2
    h8 = new Hand();
    h8.appendCard(new Card(27));
    h8.appendCard(new Card(14));
    h8.appendCard(new Card(1));
    h8.appendCard(new Card(3));
    h8.appendCard(new Card(2));
    
    // J - J - J - 10 - 9
    h9 = new Hand();
    h9.appendCard(new Card(11));
    h9.appendCard(new Card(24));
    h9.appendCard(new Card(37));
    h9.appendCard(new Card(10));
    h9.appendCard(new Card(9));
    
    // 10 - 8 - 6 - 5 - 2 for High Card
    h10 = new Hand();
    h10.appendCard(new Card(23));
    h10.appendCard(new Card(8));
    h10.appendCard(new Card(6));
    h10.appendCard(new Card(5));
    h10.appendCard(new Card(2));
    
    // A - 5 - 4 - 3 - 2 for flush royale
    h11 = new Hand();
    h11.appendCard(new Card(1));
    h11.appendCard(new Card(5));
    h11.appendCard(new Card(4));
    h11.appendCard(new Card(3));
    h11.appendCard(new Card(2));
    
    
    // A - K - Q - J - 10 for flush royale
    h12 = new Hand();
    h12.appendCard(new Card(14)); 
    h12.appendCard(new Card(26));
    h12.appendCard(new Card(25));
    h12.appendCard(new Card(24));
    h12.appendCard(new Card(23));

    // A - 9 - 5 - 3 - 2 for flush
    h13 = new Hand();
    h13.appendCard(new Card(40));
    h13.appendCard(new Card(48));
    h13.appendCard(new Card(44));
    h13.appendCard(new Card(42));
    h13.appendCard(new Card(41));
    
    // 9 - 5 - 4 - 3 - 2 for flush
    h14 = new Hand();
    h14.appendCard(new Card(9));
    h14.appendCard(new Card(5));
    h14.appendCard(new Card(4));
    h14.appendCard(new Card(3));
    h14.appendCard(new Card(2));
    
    // 3 - 3 - 2 - 2 - 2
    h15 = new Hand();
    h15.appendCard(new Card(28));
    h15.appendCard(new Card(3));
    h15.appendCard(new Card(16));
    h15.appendCard(new Card(2));
    h15.appendCard(new Card(15));
    
    // 10 - 3 - 3 - 2 - 2
    h16 = new Hand();
    h16.appendCard(new Card(10));
    h16.appendCard(new Card(3));
    h16.appendCard(new Card(16));
    h16.appendCard(new Card(2));
    h16.appendCard(new Card(15));
  }
  
  public void test_NotNull() {
    assertNotNull(new Classifier(createHand(5)));
  }
  
  public void test_NullLow() {
    try {
      new Classifier(createHand(4));
      fail("IllegalArgument not thrown");
    } catch(IllegalArgumentException e) {}
  }
    
  public void test_IsFlush() throws Exception {
    Method method = Classifier.class.getDeclaredMethod("isFlush");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h2);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h3);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h4);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h5);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h6);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h7);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h8);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h9);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertTrue(((Boolean)method.invoke(c)).booleanValue()); // A - 2 - 3 - 4 - 5 for flush royale
    assertEquals(c.nextBestCard().getValue(), 1); // Here A is part of a straight thus is low but isStraightFlush is never called
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    c = new Classifier(h12); // 10 - J - Q - K - A for flush royale
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 12);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    c = new Classifier(h13); // 2 - 3 - 5 - 9 - A for flush
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    c = new Classifier(h14); // 2 - 3 - 4 - 5 - 9 for flush
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    c = new Classifier(h15);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
  }
  
  public void test_isStraight() throws Exception {
    Method method = Classifier.class.getDeclaredMethod("isStraight");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h2);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h3);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 1);
    c = new Classifier(h4);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 12);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    c = new Classifier(h5);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.nextBestCard().getValue(), 8);
    assertEquals(c.nextBestCard().getValue(), 7);
    c = new Classifier(h6);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h7);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h8);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h9);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);;
    assertEquals(c.nextBestCard().getValue(), 1);
    c = new Classifier(h12);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 12);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    c = new Classifier(h13);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h14);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h15);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
  }
  
  public void test_IsPair() throws Exception{
    Method method = Classifier.class.getDeclaredMethod("isPair");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h2);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h3);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h4);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h5);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h6);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h7);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 7);
    assertEquals(c.nextBestCard().getValue(), 6);
    assertEquals(c.nextBestCard().getValue(), 5);
    c = new Classifier(h8);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h9);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h12);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h13);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h14);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h15);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
  }
  
  public void test_isQuad() throws Exception{
    Method method = Classifier.class.getDeclaredMethod("isQuad");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h2);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    c = new Classifier(h3);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h4);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h5);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h6);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h7);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h8);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h9);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h12);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h13);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h14);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h15);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
  }
  
  public void test_IsHighCard() throws Exception {
    // 2 - 5 - 6 - 8 - 10 for High Card
    Classifier c = new Classifier(h10);
    Method method = Classifier.class.getDeclaredMethod("isHighCard");
    method.setAccessible(true);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.nextBestCard().getValue(), 8);
    assertEquals(c.nextBestCard().getValue(), 6);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 2);
  }
  
  public void test_IsSet() throws Exception{
    Method method = Classifier.class.getDeclaredMethod("isSet");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h2);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h3);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h4);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h5);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h6);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h7);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h8);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    c = new Classifier(h9);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.nextBestCard().getValue(), 9);
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h12);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h13);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h14);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h15);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    
  }
  
  public void test_IsTwoPair() throws Exception{
     Method method = Classifier.class.getDeclaredMethod("isTwoPair");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h2);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h3);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h4);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h5);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h6);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 12);
    c = new Classifier(h7);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h8);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h9);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h12);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h13);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h14);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h15);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h16);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 10);
  }
  
  public void test_IsFullHouse() throws Exception{
    Method method = Classifier.class.getDeclaredMethod("isFullHouse");
    method.setAccessible(true);
    Classifier c = new Classifier(h1);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    c = new Classifier(h2);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h3);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h4);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h5);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h6);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h7);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h8);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h9);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h10);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h11);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h12);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h13);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h14);
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    c = new Classifier(h15);
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 3);
  }
  
  public void test_Classifier() {
    // J - J - J - 10 - 10
    Classifier c = new Classifier(h1);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.getHandType(), new HandType(HandType.FULL_HOUSE));
    
    // J - J - J - J - 10
    c = new Classifier(h2);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.getHandType(), new HandType(HandType.QUAD));
    
    // 1 - 5 - 4 - 3 - 2 straight different suits
    c = new Classifier(h3);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
    
    // A - K - Q - J - 10 straight different suits
    c = new Classifier(h4);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 12);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
    
    // J - 10 - 9 - 8 - 7 straight different suits
    c = new Classifier(h5);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.nextBestCard().getValue(), 8);
    assertEquals(c.nextBestCard().getValue(), 7);
    assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
    
    // A - A - Q - 2 - 2
    c = new Classifier(h6);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 12);
    assertEquals(c.getHandType(), new HandType(HandType.TWO_PAIR));
    
    // K - K - 7 - 6 - 5
    c = new Classifier(h7);
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 7);
    assertEquals(c.nextBestCard().getValue(), 6);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.getHandType(), new HandType(HandType.PAIR));
    
    // A - A - A - 3 - 2
    c = new Classifier(h8);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.getHandType(), new HandType(HandType.THREE_OF_A_KIND));
    
    // J - J - J - 10 - 9
    c = new Classifier(h9);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.getHandType(), new HandType(HandType.THREE_OF_A_KIND));
    
    // 10 - 8 - 6 - 5 - 2 for High Card
    c = new Classifier(h10);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.nextBestCard().getValue(), 8);
    assertEquals(c.nextBestCard().getValue(), 6);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.getHandType(), new HandType(HandType.HIGH_CARD));
    
    // A - 5 - 4 - 3 - 2 for flush royale
    c = new Classifier(h11);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT_FLUSH));
    
    // A - K - Q - J - 10 for flush royale
    c = new Classifier(h12);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 13);
    assertEquals(c.nextBestCard().getValue(), 12);
    assertEquals(c.nextBestCard().getValue(), 11);
    assertEquals(c.nextBestCard().getValue(), 10);
    assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT_FLUSH));
    
    // A - 9 - 5 - 3 - 2 for flush
    c = new Classifier(h13);
    assertEquals(c.nextBestCard().getValue(), 1);
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.getHandType(), new HandType(HandType.FLUSH));
    
    // 9 - 5 - 4 - 3 - 2 for flush
    c = new Classifier(h14);
    assertEquals(c.nextBestCard().getValue(), 9);
    assertEquals(c.nextBestCard().getValue(), 5);
    assertEquals(c.nextBestCard().getValue(), 4);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.getHandType(), new HandType(HandType.FLUSH));
    
    // 3 - 3 - 2 - 2 - 2
    c = new Classifier(h15);
    assertEquals(c.nextBestCard().getValue(), 2);
    assertEquals(c.nextBestCard().getValue(), 3);
    assertEquals(c.getHandType(), new HandType(HandType.FULL_HOUSE));
  }
  
  public void test_CountsMap() {
    Classifier c3 = new Classifier(h5);
    assertEquals(c3.getCount(new Card(24)), 1);
    assertEquals(c3.getCount(new Card(7)), 1);
    assertEquals(c3.getCount(new Card(48)), 1);
    assertEquals(c3.getCount(new Card(36)), 1);
    assertEquals(c3.getCount(new Card(8)), 1);
    
    Classifier c2 = new Classifier(h1);
    assertEquals(c2.getCount(new Card(10)), 2);
    assertEquals(c2.getCount(new Card(11)), 3);
  }
  
  public void test_CompareTo() {
    Classifier c1 = new Classifier(h1); // J - J - J - 10 - 10
    Classifier c2 = new Classifier(h2); // J - J - J - J - 10                         
    Classifier c3 = new Classifier(h3); // 1 - 5 - 4 - 3 - 2 straight different suits 
    Classifier c4 = new Classifier(h4); // A - K - Q - J - 10 straight different suits
    Classifier c5 = new Classifier(h5); // J - 10 - 9 - 8 - 7 straight different suits
    Classifier c6 = new Classifier(h6); // A - A - Q - 2 - 2                          
    Classifier c7 = new Classifier(h7); // K - K - 7 - 6 - 5                          
    Classifier c8 = new Classifier(h8); // A - A - A - 3 - 2                          
    Classifier c9 = new Classifier(h9); // J - J - J - 10 - 9                         
    Classifier c10 = new Classifier(h10); // 10 - 8 - 6 - 5 - 2 for High Card           
    Classifier c11 = new Classifier(h11); // A - 5 - 4 - 3 - 2 for flush royale         
    Classifier c12 = new Classifier(h12); // A - K - Q - J - 10 for flush royale        
    Classifier c13 = new Classifier(h13); // A - 9 - 5 - 3 - 2 for flush                
    Classifier c14 = new Classifier(h14); // 9 - 5 - 4 - 3 - 2 for flush                
    Classifier c15 = new Classifier(h15); // 3 - 3 - 2 - 2 - 2
    assertTrue(c1.compareTo(c2) < 0);
    assertTrue(c2.compareTo(c1) > 0);
    assertTrue(c3.compareTo(c4) < 0);
    assertTrue(c4.compareTo(c5) > 0);
    assertTrue(c3.compareTo(c5) < 0);
    
    assertTrue(c7.compareTo(c6) < 0);
    assertTrue(c6.compareTo(c8) < 0);
    assertTrue(c8.compareTo(c9) > 0);
    assertTrue(c7.compareTo(c10) > 0);
    assertTrue(c5.compareTo(c8) > 0);
    
    assertTrue(c12.compareTo(c11) > 0);
    assertTrue(c12.compareTo(c13) > 0);
    assertTrue(c12.compareTo(c14) > 0);
    assertTrue(c12.compareTo(c15) > 0);
    
    assertTrue(c11.compareTo(c4) > 0);
    assertTrue(c13.compareTo(c14) > 0);
    assertTrue(c15.compareTo(c13) > 0);
    assertTrue(c15.compareTo(c4) > 0);
    assertTrue(c1.compareTo(c15) > 0);
    assertTrue(c2.compareTo(c15) > 0);
  }
  
  // This tests mostly 7 card hands
  public void test_SecondBest(){
    Hand h1;
    Hand h2;
    
    // Just make sure normal comparisons work as expected
    h1 = new Hand("As", "Ac", "5s", "3d", "jd", "2c", "4c");    
    h2 = new Hand("As", "Ac", "Ks", "3d", "Jd", "2c", "4c");    
    assertTrue( h1.compareTo(h2) < 0 );
    
    h1 = new Hand("2s", "3c", "4s", "5d", "6d", "2c", "4c");
    h2 = new Hand("As", "5c", "6s", "7d", "8d", "2c", "9c");    
    assertEquals( (new Classifier(h1)).getHandType(), new HandType(HandType.STRAIGHT));
    assertEquals( (new Classifier(h2)).getHandType(), new HandType(HandType.STRAIGHT));
    assertTrue( h1.compareTo(h2) < 0 );
    
    h1 = new Hand("As", "Ac", "Ks", "3d", "Qd", "2c", "4c");    
    h2 = new Hand("As", "Ac", "Ks", "3d", "Jd", "2c", "4c");    
    assertTrue( h1.compareTo(h2) > 0 );

    h1 = new Hand("2s", "2c", "Ks", "3d", "Qd", "5c", "4c");    
    h2 = new Hand("2s", "2c", "Ks", "3d", "Jd", "5c", "4c");
    assertTrue( h1.compareTo(h2) > 0 );
    
    h1 = new Hand("2s", "3s", "4s", "5s", "6s", "5c", "4c");    
    h2 = new Hand("2s", "3s", "4s", "5s", "Ks", "5c", "4c");
    assertEquals( (new Classifier(h1)).getHandType(), new HandType(HandType.STRAIGHT_FLUSH));
    assertEquals( (new Classifier(h2)).getHandType(), new HandType(HandType.FLUSH));
    assertTrue( h1.compareTo(h2) > 0 );
    
    h1 = new Hand("2s", "3s", "4s", "5s", "6s", "5c", "4c");    
    h2 = new Hand("2s", "3s", "4s", "5s", "5d", "5c", "4c");
    assertTrue( h1.compareTo(h2) > 0 );
    
    h1 = new Hand("2s", "4s", "6s", "As", "7s", "5c", "4c");    
    h2 = new Hand("2s", "4s", "6s", "As", "3s", "5c", "4c");
    assertTrue( h1.compareTo(h2) > 0 );
    
    h1 = new Hand("2s", "4s", "6s", "As", "7s", "5c", "4c");    
    h2 = new Hand("3s", "4s", "6s", "As", "7s", "5c", "4c");
    assertTrue( h1.compareTo(h2) < 0 );
    
    h1 = new Hand("js", "Kc", "Ks", "3d", "3c", "2c", "4c");    
    h2 = new Hand("As", "4c", "Ks", "3d", "3c", "2c", "4c");    
    assertTrue( h1.compareTo(h2) > 0 );
    

  }

  public void test_AceStraights() throws Exception{
    Method method = Classifier.class.getDeclaredMethod("isStraight");
    method.setAccessible(true);
    
    Classifier c = new Classifier( new Hand("As", "2c", "3d", "4h", "5c") );    
    assertTrue(((Boolean)method.invoke(c)).booleanValue());

    c = new Classifier( new Hand("As", "Ks", "qs", "jh", "0d") );
    assertTrue(((Boolean)method.invoke(c)).booleanValue());
    
    c = new Classifier( new Hand("As", "Ks", "js", "jh", "0d") );
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
    
    c = new Classifier( new Hand("As", "2c", "3c", "4c", "Ac"));
    assertFalse(((Boolean)method.invoke(c)).booleanValue());
  }
  
  private Hand createHand(int size) {
    Hand hand = new Hand();
    for (int i = 0; i < size; i++) {
      hand.appendCard(new Card(i+13));
    }
    Collections.sort(hand.getCards());
    return hand;
  }
}
