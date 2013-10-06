package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import android.text.format.Time;

public class Game {
	public int id;
	public Time duration;
	public Time date;
	
	public boolean saveGame;
	public Timer blindTimer;
	public Timer blindTimerx; 
	
	public BlindSchedule blindSchedule;
	public Blind blind;
	
	private Table players;
	
	public Game(BlindSchedule bs, Collection<Player> players) {
		this.blindSchedule = bs;
		this.players = new Table();
		
        List<Player> temp = new ArrayList<Player>( players );
        Collections.shuffle(temp);
    
        for(Player player : temp)
            this.players.insert(player);
	}
	
	public Player play() {	
		long profit;
		ArrayList<Player> winners;
		
		while (players.size() > 1) {
		    blind = blindSchedule.getBlinds(players.size());
		    players.advanceDealer();
		    GameHand hand = new GameHand(players, blind);
			
			hand.play(); // This will run the hand to completion

	        // TODO: Should we integrate the below into GameHand? 
			winners = hand.getWinners();
			
			// for each winner, update their stack accordingly
			int totalWinners = winners.size();
			profit = hand.stack/totalWinners;
			
			for (Player p: winners) {
				p.winPot(profit);
			}
			
			// if we have any player with zero stack, kick them from table!
			players.removeBroke(); // TODO: Incorporate this into table automatically
	  }
		
		return players.get(0);
	}
}
