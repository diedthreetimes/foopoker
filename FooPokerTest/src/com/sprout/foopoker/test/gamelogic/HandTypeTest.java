package com.sprout.foopoker.test.gamelogic;

import junit.framework.TestCase;

import com.sprout.foopoker.gamelogic.HandType;

public class HandTypeTest extends TestCase {

  public void test_NotNull() {
    HandType type = new HandType(HandType.STRAIGHT_FLUSH);
    assertNotNull(type);
  }
  
  public void test_ThrowExceptionMinus() {
    try{
      new HandType(-1);
      fail("IllegalArgument expected");
    } catch(IllegalArgumentException expected){ }
    
  }
  
  public void test_ThrowExceptionHighCard() {
    try{
      new HandType(HandType.HIGH_CARD-1);
      fail("IllegalArgumentException expected");
    } catch(IllegalArgumentException expected){ }
  }
  
  public void test_ThrowExceptionFlushRoyale() {
    try{
      new HandType(HandType.STRAIGHT_FLUSH+1);
      fail("IllegalArgumentException expected");
    } catch(IllegalArgumentException expected){ }
  }

  public void test_GetCorrectType() {
    HandType t1 = new HandType(HandType.STRAIGHT_FLUSH);
    assertEquals(t1.getType(), HandType.STRAIGHT_FLUSH);
    
    HandType t2 = new HandType(HandType.FLUSH);
    assertEquals(t2.getType(), HandType.FLUSH);
    
    HandType t3 = new HandType(HandType.HIGH_CARD);
    assertEquals(t3.getType(), HandType.HIGH_CARD);
  }
  
  public void test_GetMessage() {
    HandType t1 = new HandType(HandType.STRAIGHT_FLUSH);
    assertNotNull(t1.getMessage());
    
    HandType t2 = new HandType(HandType.FLUSH);
    assertNotNull(t2.getMessage());
    
    HandType t3 = new HandType(HandType.HIGH_CARD);
    assertNotNull(t3.getMessage());
  }
  
  public void test_Equals() {
    HandType t1 = new HandType(HandType.STRAIGHT_FLUSH);
    HandType t2 = new HandType(HandType.FLUSH);
    HandType t3 = new HandType(HandType.HIGH_CARD);
    HandType t4 = new HandType(HandType.STRAIGHT_FLUSH);
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
    assertFalse(t1.equals(t3));
    assertFalse(t2.equals(t3));
    assertTrue(t1.equals(t1));
    assertTrue(t1.equals(t4));
    assertTrue(t4.equals(t4));
    assertTrue(t2.equals(t2));
  }
  
  public void test_Compare() {
    HandType t1 = new HandType(HandType.STRAIGHT_FLUSH);
    HandType t2 = new HandType(HandType.FLUSH);
    HandType t3 = new HandType(HandType.HIGH_CARD);
    HandType t4 = new HandType(HandType.STRAIGHT_FLUSH);
    assertTrue(t1.compareTo(t2) > 0);
    assertTrue(t2.compareTo(t1) < 0);
    assertTrue(t1.compareTo(t3) > 0);
    assertTrue(t2.compareTo(t3) > 0);
    assertTrue(t3.compareTo(t2) < 0);
    assertTrue(t1.compareTo(t1) == 0);
    assertTrue(t1.compareTo(t4) == 0);
    assertTrue(t4.compareTo(t4) == 0);
    assertTrue(t2.compareTo(t2) == 0);
  }
}
