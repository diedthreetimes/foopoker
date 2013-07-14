package com.sprout.foopoker.test.gamelogic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sprout.foopoker.gamelogic.HandType;

public class HandTypeTest {

  @Test
  public void test_NotNull() {
    HandType type = new HandType(HandType.FLUSH_ROYALE);
    assertNotNull(type);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void test_ThrowExceptionMinus() {
    new HandType(-1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void test_ThrowExceptionHighCard() {
    new HandType(HandType.HIGH_CARD-1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void test_ThrowExceptionFlushRoyale() {
    new HandType(HandType.FLUSH_ROYALE+1);
  }

  @Test
  public void test_GetCorrectType() {
    HandType t1 = new HandType(HandType.FLUSH_ROYALE);
    assertEquals(t1.getType(), HandType.FLUSH_ROYALE);
    
    HandType t2 = new HandType(HandType.FLUSH);
    assertEquals(t2.getType(), HandType.FLUSH);
    
    HandType t3 = new HandType(HandType.HIGH_CARD);
    assertEquals(t3.getType(), HandType.HIGH_CARD);
  }
  
  @Test
  public void test_GetMessage() {
    HandType t1 = new HandType(HandType.FLUSH_ROYALE);
    assertNotNull(t1.getMessage());
    
    HandType t2 = new HandType(HandType.FLUSH);
    assertNotNull(t2.getMessage());
    
    HandType t3 = new HandType(HandType.HIGH_CARD);
    assertNotNull(t3.getMessage());
  }
  
  @Test
  public void test_Equals() {
    HandType t1 = new HandType(HandType.FLUSH_ROYALE);
    HandType t2 = new HandType(HandType.FLUSH);
    HandType t3 = new HandType(HandType.HIGH_CARD);
    HandType t4 = new HandType(HandType.FLUSH_ROYALE);
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
    assertFalse(t1.equals(t3));
    assertFalse(t2.equals(t3));
    assertTrue(t1.equals(t1));
    assertTrue(t1.equals(t4));
    assertTrue(t4.equals(t4));
    assertTrue(t2.equals(t2));
  }
  
  @Test
  public void test_Compare() {
    HandType t1 = new HandType(HandType.FLUSH_ROYALE);
    HandType t2 = new HandType(HandType.FLUSH);
    HandType t3 = new HandType(HandType.HIGH_CARD);
    HandType t4 = new HandType(HandType.FLUSH_ROYALE);
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
