package com.sprout.foopoker.gamelogic;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class ClassifierTest {
	
	Hand h1; // J - J - J - 10 - 10
	Hand h2; // J - J - J - J - 10
	Hand h3; // 1 - 2 - 3 - 4 - 5 straight different suits
	Hand h4; // 10 - J - Q - K - A straight different suits
	Hand h5; // 7 - 8 - 9 - 10 - J straight different suits
	Hand h6; // 2 - 2 - A - A - Q
	Hand h7; // K - K - 5 - 6 - 7
	Hand h8; // A - A - A - 2 - 3
	Hand h9; // 9 - 10 - J - J - J
	Hand h10; // 2 - 5 - 6 - 8 - 10 for High Card
	Hand h11; // A - 2 - 3 - 4 - 5 for flush royale
	Hand h12; // 10 - J - Q - K - A for flush royale
	Hand h13; // 2 - 3 - 5 - 9 - A for flush
	Hand h14; // 2 - 3 - 4 - 5 - 9 for flush

	@Before
	public void setUp() throws Exception {
		// J - J - J - 10 - 10
		h1 = new Hand();
		h1.appendCard(new Card(10));
		h1.appendCard(new Card(23));
		h1.appendCard(new Card(37));
		h1.appendCard(new Card(24));
		h1.appendCard(new Card(11));
		
		// J - J - J - J - 10
		h2 = new Hand();
		h2.appendCard(new Card(23));
		h2.appendCard(new Card(50));
		h2.appendCard(new Card(37));
		h2.appendCard(new Card(24));
		h2.appendCard(new Card(11));
		
		// 1 - 2 - 3 - 4 - 5 straight different suits
		h3 = new Hand();
		h3.appendCard(new Card(15));
		h3.appendCard(new Card(16));
		h3.appendCard(new Card(30));
		h3.appendCard(new Card(31));
		h3.appendCard(new Card(1));
		
		// 10 - J - Q - K - A straight different suits
		h4 = new Hand();
		h4.appendCard(new Card(10));
		h4.appendCard(new Card(24));
		h4.appendCard(new Card(38));
		h4.appendCard(new Card(26));
		h4.appendCard(new Card(27));
		
		// 7 - 8 - 9 - 10 - J straight different suits
		h5 = new Hand();
		h5.appendCard(new Card(7));
		h5.appendCard(new Card(8));
		h5.appendCard(new Card(48));
		h5.appendCard(new Card(36));
		h5.appendCard(new Card(24));
		
		// 2 - 2 - A - A - Q
		h6 = new Hand();
		h6.appendCard(new Card(2));
		h6.appendCard(new Card(15));
		h6.appendCard(new Card(38));
		h6.appendCard(new Card(1));
		h6.appendCard(new Card(27));
		
		// K - K - 5 - 6 - 7
		h7 = new Hand();
		h7.appendCard(new Card(5));
		h7.appendCard(new Card(6));
		h7.appendCard(new Card(7));
		h7.appendCard(new Card(13));
		h7.appendCard(new Card(26));
		
		// A - A - A - 2 - 3
		h8 = new Hand();
		h8.appendCard(new Card(3));
		h8.appendCard(new Card(27));
		h8.appendCard(new Card(2));
		h8.appendCard(new Card(14));
		h8.appendCard(new Card(1));
		
		// 9 - 10 - J - J - J
		h9 = new Hand();
		h9.appendCard(new Card(9));
		h9.appendCard(new Card(10));
		h9.appendCard(new Card(11));
		h9.appendCard(new Card(24));
		h9.appendCard(new Card(37));
		
		// 2 - 5 - 6 - 8 - 10 for High Card
		h10 = new Hand();
		h10.appendCard(new Card(2));
		h10.appendCard(new Card(5));
		h10.appendCard(new Card(6));
		h10.appendCard(new Card(8));
		h10.appendCard(new Card(23));
		
		// A - 2 - 3 - 4 -5 for flush royale
		h11 = new Hand();
		h11.appendCard(new Card(2));
		h11.appendCard(new Card(3));
		h11.appendCard(new Card(4));
		h11.appendCard(new Card(5));
		h11.appendCard(new Card(1));
		
		// 10 - J - Q - K - A for flush royale
		h12 = new Hand();
		h12.appendCard(new Card(23));
		h12.appendCard(new Card(24));
		h12.appendCard(new Card(25));
		h12.appendCard(new Card(26));
		h12.appendCard(new Card(14)); 
		
		// 2 - 3 - 5 - 9 - A for flush
		h13 = new Hand();
		h13.appendCard(new Card(41));
		h13.appendCard(new Card(42));
		h13.appendCard(new Card(44));
		h13.appendCard(new Card(48));
		h13.appendCard(new Card(40));
		
		// 2 - 3 - 4 - 5 - 9 for flush
		h14 = new Hand();
		h14.appendCard(new Card(2));
		h14.appendCard(new Card(3));
		h14.appendCard(new Card(4));
		h14.appendCard(new Card(5));
		h14.appendCard(new Card(9));
	}
	
	@Test
	public void test_NotNull() {
		assertNotNull(new Classifier(createHand(5)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_NullLow() {
		new Classifier(createHand(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_NullHigh() {
		new Classifier(createHand(6));
	}
	
	@Test
	public void test_GetStraightUsingCreateHand() {
		Classifier c1 = new Classifier(createHand(5, 1));
		Classifier c2 = new Classifier(createHand(5, 14));
		Classifier c3 = new Classifier(createHand(5, 27));
		Classifier c4 = new Classifier(createHand(5, 40));
		Classifier c5 = new Classifier(createHand(5, 39));
		Classifier c6 = new Classifier(createHand(5, 3));
		Classifier c7 = new Classifier(createHand(5, 10));
		Classifier c8 = new Classifier(createHand(5, 48));
		Classifier c9 = new Classifier(createHand(5, 25));
		Card card1 = c1.getStraight();
		Card card2 = c2.getStraight();
		Card card3 = c3.getStraight();
		Card card4 = c4.getStraight();
		Card card5 = c5.getStraight();
		Card card6 = c6.getStraight();
		Card card7 = c7.getStraight();
		Card card8 = c8.getStraight();
		Card card9 = c9.getStraight();
		assertEquals(card1, new Card(5));
		assertEquals(card2, new Card(18));
		assertEquals(card3, new Card(31));
		assertEquals(card4, new Card(44));
		assertNull(card5);
		assertEquals(card6, new Card(7));
		assertEquals(card7, new Card(14));
		assertEquals(card8, new Card(52));
		assertNull(card9);
	}
	
	@Test
	public void test_GetFlushUsingCreateHand() {
		Classifier c1 = new Classifier(createHand(5, 1));
		Classifier c2 = new Classifier(createHand(5, 14));
		Classifier c3 = new Classifier(createHand(5, 27));
		Classifier c4 = new Classifier(createHand(5, 40));
		Classifier c5 = new Classifier(createHand(5, 39));
		Classifier c6 = new Classifier(createHand(5, 3));
		Classifier c7 = new Classifier(createHand(5, 10));
		Classifier c8 = new Classifier(createHand(5, 48));
		Classifier c9 = new Classifier(createHand(5, 25));
		Card card1 = c1.getFlush();
		Card card2 = c2.getFlush();
		Card card3 = c3.getFlush();
		Card card4 = c4.getFlush();
		Card card5 = c5.getFlush();
		Card card6 = c6.getFlush();
		Card card7 = c7.getFlush();
		Card card8 = c8.getFlush();
		Card card9 = c9.getFlush();
		assertEquals(card1, new Card(1));
		assertEquals(card2, new Card(14));
		assertEquals(card3, new Card(27));
		assertEquals(card4, new Card(40));
		assertNull(card5);
		assertEquals(card6, new Card(7));
		assertNull(card7);
		assertEquals(card8, new Card(52));
		assertNull(card9);
	}
	
	@Test
	public void test_GetFlush() {
		Classifier c = new Classifier(h1);
		assertNull(c.getFlush());
		c = new Classifier(h2);
		assertNull(c.getFlush());
		c = new Classifier(h3);
		assertNull(c.getFlush());
		c = new Classifier(h4);
		assertNull(c.getFlush());
		c = new Classifier(h5);
		assertNull(c.getFlush());
		c = new Classifier(h6);
		assertNull(c.getFlush());
		c = new Classifier(h7);
		assertNull(c.getFlush());
		c = new Classifier(h8);
		assertNull(c.getFlush());
		c = new Classifier(h9);
		assertNull(c.getFlush());
		c = new Classifier(h10);
		assertNull(c.getFlush());
		c = new Classifier(h11);
		assertEquals(c.getFlush().getValue(), 1);
		c = new Classifier(h12);
		assertEquals(c.getFlush().getValue(), 1);
		c = new Classifier(h13);
		assertEquals(c.getFlush().getValue(), 1);
		c = new Classifier(h14);
		assertEquals(c.getFlush().getValue(), 9);
	}
	
	@Test
	public void test_GetStraight() {
		Classifier c = new Classifier(h1);
		assertNull(c.getStraight());
		c = new Classifier(h2);
		assertNull(c.getStraight());
		c = new Classifier(h3);
		assertEquals(c.getStraight().getValue(), 5);
		c = new Classifier(h4);
		assertEquals(c.getStraight().getValue(), 1);
		c = new Classifier(h5);
		assertEquals(c.getStraight().getValue(), 11);
		c = new Classifier(h6);
		assertNull(c.getStraight());
		c = new Classifier(h7);
		assertNull(c.getStraight());
		c = new Classifier(h8);
		assertNull(c.getStraight());
		c = new Classifier(h9);
		assertNull(c.getStraight());
		c = new Classifier(h10);
		assertNull(c.getStraight());
		c = new Classifier(h11);
		assertEquals(c.getStraight().getValue(), 5);
		c = new Classifier(h12);
		assertEquals(c.getStraight().getValue(), 1);
		c = new Classifier(h13);
		assertNull(c.getStraight());
		c = new Classifier(h14);
		assertNull(c.getStraight());
	}
	
	@Test
	public void test_GetPair() {
		Classifier c = new Classifier(h1);
		assertNull(c.getPair());
		c = new Classifier(h2);
		assertNull(c.getPair());
		c = new Classifier(h3);
		assertNull(c.getPair());
		c = new Classifier(h4);
		assertNull(c.getPair());
		c = new Classifier(h5);
		assertNull(c.getPair());
		c = new Classifier(h6);
		assertNull(c.getPair());
		c = new Classifier(h7);
		assertEquals(c.getPair().getValue(), 13);
		c = new Classifier(h8);
		assertNull(c.getPair());
		c = new Classifier(h9);
		assertNull(c.getPair());
		c = new Classifier(h10);
		assertNull(c.getPair());
		c = new Classifier(h11);
		assertNull(c.getPair());
		c = new Classifier(h12);
		assertNull(c.getPair());
		c = new Classifier(h13);
		assertNull(c.getPair());
		c = new Classifier(h14);
		assertNull(c.getPair());
	}
	
	@Test
	public void test_GetQuad() {		
		Classifier c = new Classifier(h1);
		assertNull(c.getQuad());
		c = new Classifier(h2);
		assertEquals(c.getQuad().getValue(), 11);
		c = new Classifier(h3);
		assertNull(c.getQuad());
		c = new Classifier(h4);
		assertNull(c.getQuad());
		c = new Classifier(h5);
		assertNull(c.getQuad());
		c = new Classifier(h6);
		assertNull(c.getQuad());
		c = new Classifier(h7);
		assertNull(c.getQuad());
		c = new Classifier(h8);
		assertNull(c.getQuad());
		c = new Classifier(h9);
		assertNull(c.getQuad());
		c = new Classifier(h10);
		assertNull(c.getQuad());
		c = new Classifier(h11);
		assertNull(c.getQuad());
		c = new Classifier(h12);
		assertNull(c.getQuad());
		c = new Classifier(h13);
		assertNull(c.getQuad());
		c = new Classifier(h14);
		assertNull(c.getQuad());
	}
	
	@Test
	public void test_GetHighCard() {
		Classifier c = new Classifier(h1);
		assertEquals(c.getHighCard().getValue(), 11);
		c = new Classifier(h2);
		assertEquals(c.getHighCard().getValue(), 11);
		c = new Classifier(h3);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h4);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h5);
		assertEquals(c.getHighCard().getValue(), 11);
		c = new Classifier(h6);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h7);
		assertEquals(c.getHighCard().getValue(), 13);
		c = new Classifier(h8);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h9);
		assertEquals(c.getHighCard().getValue(), 11);
		c = new Classifier(h10);
		assertEquals(c.getHighCard().getValue(), 10);
		c = new Classifier(h11);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h12);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h13);
		assertEquals(c.getHighCard().getValue(), 1);
		c = new Classifier(h14);
		assertEquals(c.getHighCard().getValue(), 9);
	}
	
	@Test
	public void test_GetThreeOfAKind() {
		Classifier c = new Classifier(h1);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h2);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h3);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h4);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h5);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h6);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h7);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h8);
		assertEquals(c.getThreeOfAKind().getValue(), 1);
		c = new Classifier(h9);
		assertEquals(c.getThreeOfAKind().getValue(), 11);
		c = new Classifier(h10);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h11);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h12);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h13);
		assertNull(c.getThreeOfAKind());
		c = new Classifier(h14);
		assertNull(c.getThreeOfAKind());
	}
	
	@Test
	public void test_TwoPair() {
		Classifier c = new Classifier(h1);
		assertNull(c.getTwoPair());
		c = new Classifier(h2);
		assertNull(c.getTwoPair());
		c = new Classifier(h3);
		assertNull(c.getTwoPair());
		c = new Classifier(h4);
		assertNull(c.getTwoPair());
		c = new Classifier(h5);
		assertNull(c.getTwoPair());
		c = new Classifier(h6);
		assertEquals(c.getTwoPair().getValue(), 1);
		c = new Classifier(h7);
		assertNull(c.getTwoPair());
		c = new Classifier(h8);
		assertNull(c.getTwoPair());
		c = new Classifier(h9);
		assertNull(c.getTwoPair());
		c = new Classifier(h10);
		assertNull(c.getTwoPair());
		c = new Classifier(h11);
		assertNull(c.getTwoPair());
		c = new Classifier(h12);
		assertNull(c.getTwoPair());
		c = new Classifier(h13);
		assertNull(c.getTwoPair());
		c = new Classifier(h14);
		assertNull(c.getTwoPair());
	}
	
	@Test
	public void test_FullHouse() {
		Classifier c = new Classifier(h1);
		assertEquals(c.getFullHouse().getValue(), 11);
		c = new Classifier(h2);
		assertNull(c.getFullHouse());
		c = new Classifier(h3);
		assertNull(c.getFullHouse());
		c = new Classifier(h4);
		assertNull(c.getFullHouse());
		c = new Classifier(h5);
		assertNull(c.getFullHouse());
		c = new Classifier(h6);
		assertNull(c.getFullHouse());
		c = new Classifier(h7);
		assertNull(c.getFullHouse());
		c = new Classifier(h8);
		assertNull(c.getFullHouse());
		c = new Classifier(h9);
		assertNull(c.getFullHouse());
		c = new Classifier(h10);
		assertNull(c.getFullHouse());
		c = new Classifier(h11);
		assertNull(c.getFullHouse());
		c = new Classifier(h12);
		assertNull(c.getFullHouse());
		c = new Classifier(h13);
		assertNull(c.getFullHouse());
		c = new Classifier(h14);
		assertNull(c.getFullHouse());
	}
	
	@Test
	public void test_Classifier() {
		Classifier c = new Classifier(h1);
		assertEquals(c.getBestCard().getValue(), 11);
		System.out.println(c.getHandType().getMessage());
		assertEquals(c.getHandType(), new HandType(HandType.FULL_HOUSE));
		c = new Classifier(h2);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getHandType(), new HandType(HandType.QUAD));
		c = new Classifier(h3);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
		c = new Classifier(h4);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
		c = new Classifier(h5);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
		c = new Classifier(h6);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getHandType(), new HandType(HandType.TWO_PAIR));
		c = new Classifier(h7);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getHandType(), new HandType(HandType.PAIR));
		c = new Classifier(h8);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getHandType(), new HandType(HandType.THREE_OF_A_KIND));
		c = new Classifier(h9);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getHandType(), new HandType(HandType.THREE_OF_A_KIND));
	}
	
	@Test
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
	
	private Hand createHand(int size) {
		Hand hand = new Hand();
		for (int i = 0; i < size; i++) {
			hand.appendCard(new Card(i+13));
		}
		Collections.sort(hand.getCards());
		return hand;
	}
	
	private Hand createHand(int size, int start) {
		Hand hand = new Hand();
		for (int i = 0; i < size; i++) {
			hand.appendCard(new Card(i+start));
		}
		Collections.sort(hand.getCards());
		return hand;
	}
}
