package com.sprout.foopoker.gamelogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClassifierTest {
	
	Hand h1; // J - J - J - 10 - 10
	Hand h2; // J - J - J - J - 10
	Hand h3; // 1 - 2 - 3 - 4 - 5
	Hand h4; // 10 - J - Q - K - A
	Hand h5; // 7 - 8 - 9 - 10 - J
	Hand h6; // 2 - 2 - A - A - Q
	Hand h7; // K - K - 5 - 6 - 7
	Hand h8; // A - A - A - 2 - 3
	Hand h9; // 9 - 10 - J - J - J

	@Before
	public void setUp() throws Exception {
		// J - J - J - 10 - 10
		h1 = new Hand();
		h1.appendCard(new Card(10));
		h1.appendCard(new Card(37));
		h1.appendCard(new Card(24));
		h1.appendCard(new Card(23));
		h1.appendCard(new Card(11));
		
		// J - J - J - J - 10
		h2 = new Hand();
		h2.appendCard(new Card(50));
		h2.appendCard(new Card(37));
		h2.appendCard(new Card(24));
		h2.appendCard(new Card(23));
		h2.appendCard(new Card(11));
		
		// 1 - 2 - 3 - 4 - 5
		h3 = new Hand();
		h3.appendCard(new Card(16));
		h3.appendCard(new Card(30));
		h3.appendCard(new Card(15));
		h3.appendCard(new Card(1));
		h3.appendCard(new Card(31));
		
		// 10 - J - Q - K - A
		h4 = new Hand();
		h4.appendCard(new Card(10));
		h4.appendCard(new Card(38));
		h4.appendCard(new Card(27));
		h4.appendCard(new Card(24));
		h4.appendCard(new Card(26));
		
		// 7 - 8 - 9 - 10 - J
		h5 = new Hand();
		h5.appendCard(new Card(24));
		h5.appendCard(new Card(7));
		h5.appendCard(new Card(48));
		h5.appendCard(new Card(36));
		h5.appendCard(new Card(8));
		
		// 2 - 2 - A - A - Q
		h6 = new Hand();
		h6.appendCard(new Card(2));
		h6.appendCard(new Card(27));
		h6.appendCard(new Card(38));
		h6.appendCard(new Card(1));
		h6.appendCard(new Card(15));
		
		// K - K - 5 - 6 - 7
		h7 = new Hand();
		h7.appendCard(new Card(5));
		h7.appendCard(new Card(13));
		h7.appendCard(new Card(6));
		h7.appendCard(new Card(7));
		h7.appendCard(new Card(26));
		
		// A - A - A - 2 - 3
		h8 = new Hand();
		h8.appendCard(new Card(2));
		h8.appendCard(new Card(14));
		h8.appendCard(new Card(1));
		h8.appendCard(new Card(3));
		h8.appendCard(new Card(27));
		
		// 9 - 10 - J - J - J
		h9 = new Hand();
		h9.appendCard(new Card(11));
		h9.appendCard(new Card(10));
		h9.appendCard(new Card(9));
		h9.appendCard(new Card(24));
		h9.appendCard(new Card(37));
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
	public void test_GetStraight() {
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
		assertEquals(card1, new Card(1));
		assertEquals(card2, new Card(14));
		assertEquals(card3, new Card(27));
		assertEquals(card4, new Card(40));
		assertNull(card5);
		assertEquals(card6, new Card(7));
		assertEquals(card7, new Card(14));
		assertEquals(card8, new Card(52));
		assertNull(card9);
	}
	
	@Test
	public void test_GetStraightDifferentSuits() {

		Classifier c1 = new Classifier(h3);
		Card card1 = c1.getStraight();
		assertEquals(card1, new Card(1));

		Classifier c2 = new Classifier(h4);
		Card card2 = c2.getStraight();
		assertEquals(card2, new Card(27));

		Classifier c3 = new Classifier(h5);
		Card card3 = c3.getStraight();
		assertEquals(card3, new Card(24));
	}
	
	@Test
	public void test_GetFlush() {
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
		assertNull(c.getQuad());
		c = new Classifier(h9);
		assertNull(c.getQuad());
	}
	
	@Test
	public void test_GetQuad() {		
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
	
	@Test
	public void test_FindHighCard() {
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
	}
	
	@Test
	public void test_TwoPair() {
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
	}
	
	private Hand createHand(int size) {
		Hand hand = new Hand();
		for (int i = 0; i < size; i++) {
			hand.appendCard(new Card(i+13));
		}
		return hand;
	}
	
	private Hand createHand(int size, int start) {
		Hand hand = new Hand();
		for (int i = 0; i < size; i++) {
			hand.appendCard(new Card(i+start));
		}
		return hand;
	}
}
