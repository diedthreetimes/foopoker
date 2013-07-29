package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private Table players;
	
	public Game(long smallBlind, long bigBlind, long ante) {
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
		this.ante = ante;
		this.players = new Table();
	}
	
	public void play() {
		int totalWinners, winners[];
		long profit;
		
		while (players.size() > 1) {
			GameHand hand = new GameHand();
			winners = new int[0];//= GameCourt.getWinner(hand);
			
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
	  
	  ArrayList<Player> temp = (ArrayList<Player>) newPlayers.clone();
	  Collections.shuffle(temp);
	  
	  for(Player player : temp){
	    players.insert(player);
	  }
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
