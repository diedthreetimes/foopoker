package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.annotation.SuppressLint;


/**
 * GameCourt will decide the winner(s)!
 * Here are the rules: http://en.wikipedia.org/wiki/List_of_poker_hands
 * @author ekinoguz
 * 
 * possible TODO's:
 * -> Go over the implementation of combination. It is copied from stackoverflow
 * -> This class is written so fast, there must be some unnecessary operations
 */

@SuppressLint("UseSparseArrays")
public class GameCourt {
  /** players who will be compared */
  private ArrayList<Player> players;
  
  /** hands of the players*/
  private ArrayList<Card> fiveCards;
  
  /**
   * @param fiveCards
   * @param players
   */
  public GameCourt(ArrayList<Card> fiveCards, ArrayList<Player> players) 
    throws IllegalArgumentException {
    
    if (fiveCards.size() != 5) {
      throw new IllegalArgumentException("GameCourt needs 5 cards");
    } else if (players.size() < 1) {
      throw new IllegalArgumentException("If there are not any players, then" +
          "please do not bother me");
    }
    this.fiveCards = fiveCards;
    this.players = players;  
    
    process();
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
   * process the fiveCards and players' hands 
   * -> append all the table cards to each Player's hand
   * -> sort each Player's hand
   * -> find each Player's best hand
   */
  private void process() {
    // for each Player
    for (Player player : players) {
      // append all table cards to each Player's hand
      player.appendCards(fiveCards);
    }
  }
}
