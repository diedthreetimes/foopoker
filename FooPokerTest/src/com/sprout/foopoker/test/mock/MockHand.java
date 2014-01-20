package com.sprout.foopoker.test.mock;

import com.sprout.foopoker.gamelogic.*;

import java.util.List;

// A small helper class for writing test cases
class MockHand {
	public List<Player> players;
	public MockDealer dealer;
	public GameHand gh;

	public MockHand(List<Player> players, MockDealer dealer, GameHand gh){
		this.players = players;
		this.dealer = dealer;
		this.gh = gh;
	}
}
