package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Timer;

import android.text.format.Time;

public class Game {

	
	public int id;
	public Time duration;
	public Time date;
	
	public boolean saveGame;
	public Timer blindTimer;
	public Timer blindTimerx; 
	
	public long smallBlind;
	public long bigBlind;
	public long ante;
	
	private CircularPlayers players;
	
	public Game(long smallBlind, long bigBlind, long ante) {
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
		this.ante = ante;
		this.players = new CircularPlayers();
	}
	
	public void play() {
		int totalWinners, winners[];
		long profit;
		
		while (players.size() > 1) {
			Hand hand = new Hand();
			winners = GameCourt.getWinner(hand);
			
			// for each winner, update their stack accordingly
			totalWinners = winners.length;
			profit = hand.stack/totalWinners;
			
			for (int i = 0; i < winners.length; i++) {
				players.get(winners[i]).winPot(profit);
			}
			
			// update each persons stack accordingly
			
			// if we have any player with zero stack, kick them from table!
		}
	}
	
	/**
	 * Start the game with given newPlayers
	 * @param newPlayers
	 */
	public void startGame(ArrayList<Player> newPlayers) {
		// Pick random numbers for each player, 
		// 		and decide who is going to be the dealer.
		int best = Integer.MIN_VALUE, bestIndex = 0, local;
		for (int i = 0; i < newPlayers.size(); i++) {
			local = (int) Math.random() * 1000;
			if (local > best) {
				best = local;
				bestIndex = i;
			}
		}
		
		// bestIndex is the dealer, add it first to CircularPlayers
		// 	add rest in the given order
		players.insert(newPlayers.get(bestIndex));
		for (int i = bestIndex+1; i < newPlayers.size(); i++)
			players.insert(newPlayers.get(i));
		for (int i = 0; i < bestIndex; i++)
			players.insert(newPlayers.get(i));
		
	}
	
	/**
	 * Double the blinds
	 * Start the blind timer
	 */
	private void updateBlinds() {
		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
