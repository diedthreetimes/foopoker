package com.sprout.foopoker.test.mock;

import com.sprout.foopoker.gamelogic.*;

import java.util.List;

// A small helper class for writing test cases
// Its primary function is to allow preconfigured deals
class MockHand {
	public List<Player> players;
	public MockDealer dealer;
	public GameHand gh; // The game this is monitoring

	public MockHand(List<Player> players, MockDealer dealer, GameHand gh){
		this.players = players;
		this.dealer = dealer;
		this.gh = gh;
	}
}
