package com.sprout.foopoker.gamelogic;

import static org.junit.Assert.*;

import org.junit.Test;

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
	public void test_getCorrectType() {
		HandType t1 = new HandType(HandType.FLUSH_ROYALE);
		assertEquals(t1.getType(), HandType.FLUSH_ROYALE);
		
		HandType t2 = new HandType(HandType.FLUSH);
		assertEquals(t2.getType(), HandType.FLUSH);
		
		HandType t3 = new HandType(HandType.HIGH_CARD);
		assertEquals(t3.getType(), HandType.HIGH_CARD);
	}
	
	@Test
	public void test_getMessage() {
		HandType t1 = new HandType(HandType.FLUSH_ROYALE);
		assertNotNull(t1.getMessage());
		
		HandType t2 = new HandType(HandType.FLUSH);
		assertNotNull(t2.getMessage());
		
		HandType t3 = new HandType(HandType.HIGH_CARD);
		assertNotNull(t3.getMessage());
	}
}
