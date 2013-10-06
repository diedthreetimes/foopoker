package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;

public class GameHand {

	public int id;
    
    public long pot;
	
	public Blind blind;
	
	public Dealer dealer;
	public Table players;
	
    // What is the meaning of this ENUM
	public enum Status { STARTED, PLAYING, FINISHED }
	public Status status;
	
    // These are the shared table cards.
	private ArrayList<Card> fiveCards;
	// This may not be necessary, but it simplifies betting logic
	private HashMap<Player, Integer> current_bets; 
	
	public GameHand(Table players, Blind blind) {
		dealer = Dealer.getInstance();
		fiveCards = new ArrayList<Card>();
		current_bets = new HashMap<Player, Integer>();		
		this.players = players;
		this.blind = blind;
		
		clearBets();
	}
	
	
	/**
	 * Reset bets to 0
	 */
	private void clearBets() {
		for(int i =0; i < players.size(); i++)
			current_bets.put( players.get(i), -1);
	}
	
	/**
	 * Returns the current highest bet
	 */
	private int maxBet() {
		int maxbet = 0;
		for(int bet : current_bets.values())
			if(bet > maxbet)
				maxbet = bet;
				
		return maxbet;
	}
	
	/**
	 * Deal new hand. Deal the card
	 */
	private void startNewHand() {
		status = Status.STARTED;
		dealer.shuffle();
		
		// Post blinds and antes, and deal
		placeBet(players.getBigBlind(),blind.getBig());
		placeBet(players.getSmallBlind(),blind.getSmall());
		
		for( int i=0; i < players.size(); i++){
			placeBet(players.get(i),blind.getAnte());
			
			for( int j=0; j < 2; j++) // 2 is the size of the pocket
				players.get(i).appendCard(dealer.dealCard());
		}
	}
	
	// Play should play the hand to conclusion.
	public void play() {
		startNewHand();
	 
	 	// Rounds are preflop, flop, turn, river
	 	int [] cards_per_round = {0,3,1,1};
	 	for(int i = 0; i < cards_per_round.length; i++)
			dealShared(cards_per_round[i]);
		if(!bettingRound()) {
			// Only one player left no need to continue
			return;
		}
			
		// After the betting is over clear the bets
		clearBets();
		players.nextRound();
		
		// S->E: Is potGood supposed to be an error check? 
		// if (!potGood())
		//	System.out.println("asdf");
	}
	
	public boolean bettingRound() {
		while( current_bets.get(players.peek()) < maxBet() ) {
			Player cur_player = players.next();
			if(current_bets.get(cur_player) < 0)
				current_bets.put(cur_player,0);
			// Action action = players.next().play();
			// TODO: Play should include the actions available to player. the current bet to player.
			//      Do we need to pass any other information? 
			// TODO: Process action
			
			if(players.numActive() <= 0)
				return false;
		}
		return true;
	}
	
	public void dealShared(int num_cards){
		for(int i=0; i < num_cards; i++){
			Card new_card = dealer.dealCard();
			
			fiveCards.add(new_card);
			for(int j=0; j < players.size(); j++)
				players.get(j).appendCard(new_card);
		}
	}
	
	public void saveHand() {
		
	}
	
	// What is this supposed to check for?
	//public boolean potGood() {
	//	return true;
	//}
	
	public String toString() {
		return "TODO: implement toString";
	}
	
	/**
	 * Place a players bet into the pot.
	 *  If player has inseufficient funds for the bet pot may be split
	 */
	private void placeBet(Player p, int bet){
		// TODO: Split pot if p has insufficient funds.
		p.bet(bet);
		pot += bet;
		
		current_bets.put(p, current_bets.get(p)+bet);
	}
	
	/**
	 * @return the winnings from this hand
	 */
	public long getPot() {
		return pot;
	}
	
	/**
	* @return the players who win the game
	*/
	public ArrayList<Player> getWinners() {
    	ArrayList<Player> bestPlayers = new ArrayList<Player>();
    	bestPlayers.add(players.get(0));
    	if (players.size() == 1) {
      		return bestPlayers;
    	}
    	int compareValue;
    	for (int i = 1; i < players.size(); i++) {
			
			// This bit of hackery is to ensure that we don't process dead hands
			if( players.getFolded(i) )
				continue; 
				
      		compareValue = bestPlayers.get(0).getHand().compareTo(players.get(i).getHand());
      		if (compareValue == 0) {
        		bestPlayers.add(players.get(i));
      		} else if (compareValue < 0){
        		bestPlayers.clear();
        		bestPlayers.add(players.get(i));
      		}
    	}
    	return bestPlayers;
	}

	/**
	 * @return the cards of the game hand
	 */
	public ArrayList<Card> getCards() {
		return fiveCards;
	}
}
