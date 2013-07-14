package com.sprout.foopoker.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.annotation.SuppressLint;

import com.sprout.foopoker.userdata.Player;


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
  
  /** mapping between playerId - her best hand*/
  private HashMap<Integer, Classifier> classifiers;
  
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
    this.classifiers = new HashMap<Integer, Classifier>();
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
    Classifier best = classifiers.get(players.get(0).getId());
    int compareValue;
    for (int i = 1; i < players.size(); i++) {
      compareValue = best.compareTo(classifiers.get(players.get(i).getId()));
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
      
      // now Player will have 7 cards. make sure you sorted each Player's hand
      Collections.sort(player.getCards());
      
      /**
       * find the player's best hand and update the result in
       * classifiers map. Here is the outline of algorithm:
       * 1) For each 5 Card combination of Hand, find its classifier
       * 2) Compare classifier with best classifier so far and update best
       * Note: We only have 42 different combination. It should not be
       *   very expensive to try all combination.
       */
      // res is the index to decide which cards will be selected
      int[] res = new int[5];
      for (int i = 0; i < res.length; i++) {
        res[i] = i + 1;
      }
      boolean done = false;
      while (!done) {
        Hand tempHand = new Hand();
        for (int i = 0; i < res.length; i++) {
          tempHand.appendCard(player.getCard(res[i]-1));
        }
        Classifier tempClassifier = new Classifier(tempHand);
        this.updateClassifierMap(player.getId(), tempClassifier);
        done = getNext(res, 7, 5); // because we are doing C(7,5)
      }
    }
  }
  
  /**
   * Get the next combination from num array
   * I copied this from here: http://stackoverflow.com/a/7631893/2009800
   * @param num
   * @param n
   * @param r
   * @return
   */
  private boolean getNext(final int[] num, final int n, final int r) {
    int target = r - 1;
    num[target]++;
    if (num[target] > ((n - (r - target)) + 1)) {
      // Carry the One
      while (num[target] > ((n - (r - target)))) {
        target--;
        if (target < 0) {
            break;
        }
      }
      if (target < 0) {
        return true;
      }
      num[target]++;
      for (int i = target + 1; i < num.length; i++) {
        num[i] = num[i - 1] + 1;
      }
    }
    return false;
  }
  
  /**
   * Updates the classifiers map
   * @param id the player id whose classifier will be updated
   * @param newClassifier compare it if there is already one, update accordingly
   */
  private void updateClassifierMap(int id, Classifier newClassifier) {
    // if we do not have it, simply put it
    if (!classifiers.containsKey(id)) {
      classifiers.put(id, newClassifier);
    } else {
      // update if new one is better than the previous one
      if (newClassifier.compareTo(classifiers.get(id)) > 0) {
        classifiers.put(id, newClassifier);
      }
    }
  }
}
