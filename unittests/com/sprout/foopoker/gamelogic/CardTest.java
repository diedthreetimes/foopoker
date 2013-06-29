package com.sprout.foopoker.gamelogic;

import junit.framework.TestCase;

import org.junit.Test;

import com.sprout.foopoker.gamelogic.Card.Suit;

public class CardTest extends TestCase{

	@Test
	public void test_IsCardCreated() {
		Card c1 = new Card(13);
		assertSame(c1.getValue(), 13);
	}
	
//	@Test(expected=IllegalArgumentException.class)
//	public void test_NegativeCard() {
//		new Card(-1);
//	}
	
	@Test
	public void test_IsCardSpade() {
		Card c1 = new Card(13);
		assertSame(c1.getSuit(), Suit.SPADE);
	}
	
	@Test
	public void test_IsCardHeart() {
		Card c3 = new Card(14);
		assertSame(c3.getSuit(), Suit.HEART);
	}
	
	@Test
	public void test_IsCardClub() {
		Card c2 = new Card(52);
		assertSame(c2.getSuit(), Suit.CLUB);
	}
	
	@Test
	public void test_IsCardDiamond() {
		Card c3 = new Card(27);
		assertSame(c3.getSuit(), Suit.DIAMOND);
	}
	
	@Test
	public void test_IsCardCorrectValue() {
		Card c3 = new Card(27);
		assertSame(c3.getValue(), 1);
	}
	
	@Test
	public void test_CompareBigger() {
		Card c1 = new Card(3);
		Card c2 = new Card(2);
		assertTrue(c1.compareTo(c2) > 0);
	}
	
	@Test
	public void test_CompareAce1() {
		Card c1 = new Card(1);
		Card c2 = new Card(2);
		assertTrue(c1.compareTo(c2) > 0);
	}
	
	@Test
	public void test_CompareAce2() {
		Card c1 = new Card(2);
		Card c2 = new Card(14);
		assertTrue(c1.compareTo(c2) < 0);
	}
	
	@Test
	public void test_CompareEqual() {
		Card c1 = new Card(27);
		Card c2 = new Card(14);
		assertTrue(c1.compareTo(c2) == 0);
	}

}
