package com.sprout.foopoker.gamelogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandTest {

	@Test
	public void test_NotNull() {
		Hand hand = new Hand();
		assertNotNull(hand);
	}
	
	@Test
	public void test_AddAndRetriveCorrectly() {
		Hand hand = new Hand();
		Card card = new Card(1);
		hand.appendCard(card);
		assertEquals(card, hand.getCard(0));
	}
	
	@Test
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

	@Test(expected=ArrayIndexOutOfBoundsException.class)
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
	}
	
	@Test
	public void test_NullHighCard() {
		Hand hand = new Hand();
		assertNull(hand.getHighCard());
		Card card = new Card(1);
		hand.appendCard(card);
		assertNull(hand.getHighCard());
	}
	
	@Test
	public void test_NotNullHighCard() {
		
	}
	
	public void test_EqualHand() {
		
	}
	
	public void test_BetterHand() {
		
	}
	
	public void test_WorseHand() {
		
	}
}
