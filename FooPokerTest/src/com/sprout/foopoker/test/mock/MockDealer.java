package com.sprout.foopoker.test.mock;

import com.sprout.foopoker.gamelogic.*;
import java.util.*;

public class MockDealer extends Dealer
{
	public MockDealer(){
		super();
	}
	
	public MockDealer(String card_order){
		super();
		setDeck(card_order);
	}
	
	public MockDealer(Card[] cards){
		super();
		setDeck(cards);
	}
	
	public void setDeck(String cards){
		List<Card> card_l = new ArrayList<Card>();	
		for(String card : cards.split(",") )
			card_l.add(new Card(card));
		
		this.cards = card_l.toArray(this.cards);
	}
	
	public void setDeck(Card[] cards){
		this.cards = cards;
	}
	
	public void setDeck(Collection<Card> cards){
		this.cards = cards.toArray(this.cards);
	}
	
	@Override
	public void shuffle(){
		this.position = 0;
		// Does nothing so deck order is fixed
	}
}
