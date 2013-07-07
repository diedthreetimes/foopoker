package com.sprout.foopoker.gamelogic;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class ClassifierTest {
	
	Hand h1; // J - J - J - 10 - 10
	Hand h2; // J - J - J - J - 10
	Hand h3; // 1 - 5 - 4 - 3 - 2 straight different suits
	Hand h4; // A - K - Q - J - 10 straight different suits
	Hand h5; // J - 10 - 9 - 8 - 7 straight different suits
	Hand h6; // A - A - Q - 2 - 2
	Hand h7; // K - K - 7 - 6 - 5
	Hand h8; // A - A - A - 3 - 2
	Hand h9; // J - J - J - 10 - 9
	Hand h10; // 10 - 8 - 6 - 5 - 2 for High Card
	Hand h11; // A - 5 - 4 - 3 - 2 for flush royale
	Hand h12; // A - K - Q - J - 10 for flush royale
	Hand h13; // A - 9 - 5 - 3 - 2 for flush
	Hand h14; // 9 - 5 - 4 - 3 - 2 for flush
	Hand h15; // 3 - 3 - 2 - 2 - 2

	@Before
	public void setUp() throws Exception {
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
	public void test_IsFlush() {
		Classifier c = new Classifier(h1);
		assertFalse(c.isFlush());
		c = new Classifier(h2);
		assertFalse(c.isFlush());
		c = new Classifier(h3);
		assertFalse(c.isFlush());
		c = new Classifier(h4);
		assertFalse(c.isFlush());
		c = new Classifier(h5);
		assertFalse(c.isFlush());
		c = new Classifier(h6);
		assertFalse(c.isFlush());
		c = new Classifier(h7);
		assertFalse(c.isFlush());
		c = new Classifier(h8);
		assertFalse(c.isFlush());
		c = new Classifier(h9);
		assertFalse(c.isFlush());
		c = new Classifier(h10);
		assertFalse(c.isFlush());
		c = new Classifier(h11);
		assertTrue(c.isFlush()); // A - 2 - 3 - 4 - 5 for flush royale
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		c = new Classifier(h12); // 10 - J - Q - K - A for flush royale
		assertTrue(c.isFlush());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 12);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		c = new Classifier(h13); // 2 - 3 - 5 - 9 - A for flush
		assertTrue(c.isFlush());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		c = new Classifier(h14); // 2 - 3 - 4 - 5 - 9 for flush
		assertTrue(c.isFlush());
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		c = new Classifier(h15);
		assertFalse(c.isFlush());
	}
	
	@Test
	public void test_isStraight() {
		Classifier c = new Classifier(h1);
		assertFalse(c.isStraight());
		c = new Classifier(h2);
		assertFalse(c.isStraight());
		c = new Classifier(h3);
		assertTrue(c.isStraight());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		c = new Classifier(h4);
		assertTrue(c.isStraight());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 12);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		c = new Classifier(h5);
		assertTrue(c.isStraight());
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getBestCard().getValue(), 8);
		assertEquals(c.getBestCard().getValue(), 7);
		c = new Classifier(h6);
		assertFalse(c.isStraight());
		c = new Classifier(h7);
		assertFalse(c.isStraight());
		c = new Classifier(h8);
		assertFalse(c.isStraight());
		c = new Classifier(h9);
		assertFalse(c.isStraight());
		c = new Classifier(h10);
		assertFalse(c.isStraight());
		c = new Classifier(h11);
		assertTrue(c.isStraight());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);;
		c = new Classifier(h12);
		assertTrue(c.isStraight());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 12);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		c = new Classifier(h13);
		assertFalse(c.isStraight());
		c = new Classifier(h14);
		assertFalse(c.isStraight());
		c = new Classifier(h15);
		assertFalse(c.isStraight());
	}
	
	@Test
	public void test_IsPair() {
		Classifier c = new Classifier(h1);
		assertFalse(c.isPair());
		c = new Classifier(h2);
		assertFalse(c.isPair());
		c = new Classifier(h3);
		assertFalse(c.isPair());
		c = new Classifier(h4);
		assertFalse(c.isPair());
		c = new Classifier(h5);
		assertFalse(c.isPair());
		c = new Classifier(h6);
		assertFalse(c.isPair());
		c = new Classifier(h7);
		assertTrue(c.isPair());
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 7);
		assertEquals(c.getBestCard().getValue(), 6);
		assertEquals(c.getBestCard().getValue(), 5);
		c = new Classifier(h8);
		assertFalse(c.isPair());
		c = new Classifier(h9);
		assertFalse(c.isPair());
		c = new Classifier(h10);
		assertFalse(c.isPair());
		c = new Classifier(h11);
		assertFalse(c.isPair());
		c = new Classifier(h12);
		assertFalse(c.isPair());
		c = new Classifier(h13);
		assertFalse(c.isPair());
		c = new Classifier(h14);
		assertFalse(c.isPair());
		c = new Classifier(h15);
		assertFalse(c.isPair());
	}
	
	@Test
	public void test_isQuad() {		
		Classifier c = new Classifier(h1);
		assertFalse(c.isQuad());
		c = new Classifier(h2);
		assertTrue(c.isQuad());
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		c = new Classifier(h3);
		assertFalse(c.isQuad());
		c = new Classifier(h4);
		assertFalse(c.isQuad());
		c = new Classifier(h5);
		assertFalse(c.isQuad());
		c = new Classifier(h6);
		assertFalse(c.isQuad());
		c = new Classifier(h7);
		assertFalse(c.isQuad());
		c = new Classifier(h8);
		assertFalse(c.isQuad());
		c = new Classifier(h9);
		assertFalse(c.isQuad());
		c = new Classifier(h10);
		assertFalse(c.isQuad());
		c = new Classifier(h11);
		assertFalse(c.isQuad());
		c = new Classifier(h12);
		assertFalse(c.isQuad());
		c = new Classifier(h13);
		assertFalse(c.isQuad());
		c = new Classifier(h14);
		assertFalse(c.isQuad());
		c = new Classifier(h15);
		assertFalse(c.isQuad());
	}
	
	@Test
	public void test_IsHighCard() {
		// 2 - 5 - 6 - 8 - 10 for High Card
		Classifier c = new Classifier(h10);
		assertTrue(c.isHighCard());
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getBestCard().getValue(), 8);
		assertEquals(c.getBestCard().getValue(), 6);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 2);
	}
	
	@Test
	public void test_IsSet() {
		Classifier c = new Classifier(h1);
		assertFalse(c.isSet());
		c = new Classifier(h2);
		assertFalse(c.isSet());
		c = new Classifier(h3);
		assertFalse(c.isSet());
		c = new Classifier(h4);
		assertFalse(c.isSet());
		c = new Classifier(h5);
		assertFalse(c.isSet());
		c = new Classifier(h6);
		assertFalse(c.isSet());
		c = new Classifier(h7);
		assertFalse(c.isSet());
		c = new Classifier(h8);
		assertTrue(c.isSet());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		c = new Classifier(h9);
		assertTrue(c.isSet());
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getBestCard().getValue(), 9);
		c = new Classifier(h10);
		assertFalse(c.isSet());
		c = new Classifier(h11);
		assertFalse(c.isSet());
		c = new Classifier(h12);
		assertFalse(c.isSet());
		c = new Classifier(h13);
		assertFalse(c.isSet());
		c = new Classifier(h14);
		assertFalse(c.isSet());
		c = new Classifier(h15);
		assertFalse(c.isSet());
		
	}
	
	@Test
	public void test_IsTwoPair() {
		Classifier c = new Classifier(h1);
		assertFalse(c.isTwoPair());
		c = new Classifier(h2);
		assertFalse(c.isTwoPair());
		c = new Classifier(h3);
		assertFalse(c.isTwoPair());
		c = new Classifier(h4);
		assertFalse(c.isTwoPair());
		c = new Classifier(h5);
		assertFalse(c.isTwoPair());
		c = new Classifier(h6);
		assertTrue(c.isTwoPair());
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getBestCard().getValue(), 12);
		c = new Classifier(h7);
		assertFalse(c.isTwoPair());
		c = new Classifier(h8);
		assertFalse(c.isTwoPair());
		c = new Classifier(h9);
		assertFalse(c.isTwoPair());
		c = new Classifier(h10);
		assertFalse(c.isTwoPair());
		c = new Classifier(h11);
		assertFalse(c.isTwoPair());
		c = new Classifier(h12);
		assertFalse(c.isTwoPair());
		c = new Classifier(h13);
		assertFalse(c.isTwoPair());
		c = new Classifier(h14);
		assertFalse(c.isTwoPair());
		c = new Classifier(h15);
		assertFalse(c.isTwoPair()); 
	}
	
	@Test
	public void test_IsFullHouse() {
		Classifier c = new Classifier(h1);
		assertTrue(c.isFullHouse());
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		c = new Classifier(h2);
		assertFalse(c.isFullHouse());
		c = new Classifier(h3);
		assertFalse(c.isFullHouse());
		c = new Classifier(h4);
		assertFalse(c.isFullHouse());
		c = new Classifier(h5);
		assertFalse(c.isFullHouse());
		c = new Classifier(h6);
		assertFalse(c.isFullHouse());
		c = new Classifier(h7);
		assertFalse(c.isFullHouse());
		c = new Classifier(h8);
		assertFalse(c.isFullHouse());
		c = new Classifier(h9);
		assertFalse(c.isFullHouse());
		c = new Classifier(h10);
		assertFalse(c.isFullHouse());
		c = new Classifier(h11);
		assertFalse(c.isFullHouse());
		c = new Classifier(h12);
		assertFalse(c.isFullHouse());
		c = new Classifier(h13);
		assertFalse(c.isFullHouse());
		c = new Classifier(h14);
		assertFalse(c.isFullHouse());
		c = new Classifier(h15);
		assertTrue(c.isFullHouse());
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getBestCard().getValue(), 3);
	}
	
	@Test
	public void test_Classifier() {
		// J - J - J - 10 - 10
		Classifier c = new Classifier(h1);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getHandType(), new HandType(HandType.FULL_HOUSE));
		
		// J - J - J - J - 10
		c = new Classifier(h2);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getHandType(), new HandType(HandType.QUAD));
		
		// 1 - 5 - 4 - 3 - 2 straight different suits
		c = new Classifier(h3);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
		
		// A - K - Q - J - 10 straight different suits
		c = new Classifier(h4);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 12);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
		
		// J - 10 - 9 - 8 - 7 straight different suits
		c = new Classifier(h5);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getBestCard().getValue(), 8);
		assertEquals(c.getBestCard().getValue(), 7);
		assertEquals(c.getHandType(), new HandType(HandType.STRAIGHT));
		
		// A - A - Q - 2 - 2
		c = new Classifier(h6);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getBestCard().getValue(), 12);
		assertEquals(c.getHandType(), new HandType(HandType.TWO_PAIR));
		
		// K - K - 7 - 6 - 5
		c = new Classifier(h7);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 7);
		assertEquals(c.getBestCard().getValue(), 6);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getHandType(), new HandType(HandType.PAIR));
		
		// A - A - A - 3 - 2
		c = new Classifier(h8);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getHandType(), new HandType(HandType.THREE_OF_A_KIND));
		
		// J - J - J - 10 - 9
		c = new Classifier(h9);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getHandType(), new HandType(HandType.THREE_OF_A_KIND));
		
		// 10 - 8 - 6 - 5 - 2 for High Card
		c = new Classifier(h10);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getBestCard().getValue(), 8);
		assertEquals(c.getBestCard().getValue(), 6);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getHandType(), new HandType(HandType.HIGH_CARD));
		
		// A - 5 - 4 - 3 - 2 for flush royale
		c = new Classifier(h11);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getHandType(), new HandType(HandType.FLUSH_ROYALE));
		
		// A - K - Q - J - 10 for flush royale
		c = new Classifier(h12);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 13);
		assertEquals(c.getBestCard().getValue(), 12);
		assertEquals(c.getBestCard().getValue(), 11);
		assertEquals(c.getBestCard().getValue(), 10);
		assertEquals(c.getHandType(), new HandType(HandType.FLUSH_ROYALE));
		
		// A - 9 - 5 - 3 - 2 for flush
		c = new Classifier(h13);
		assertEquals(c.getBestCard().getValue(), 1);
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getHandType(), new HandType(HandType.FLUSH));
		
		// 9 - 5 - 4 - 3 - 2 for flush
		c = new Classifier(h14);
		assertEquals(c.getBestCard().getValue(), 9);
		assertEquals(c.getBestCard().getValue(), 5);
		assertEquals(c.getBestCard().getValue(), 4);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getHandType(), new HandType(HandType.FLUSH));
		
		// 3 - 3 - 2 - 2 - 2
		c = new Classifier(h15);
		assertEquals(c.getBestCard().getValue(), 2);
		assertEquals(c.getBestCard().getValue(), 3);
		assertEquals(c.getHandType(), new HandType(HandType.FULL_HOUSE));
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

}
