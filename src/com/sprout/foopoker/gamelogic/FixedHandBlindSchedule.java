package com.sprout.foopoker.gamelogic;

/**
 * Adjust the blinds every fixed number of rounds
 * @author sjfaber
 *
 */

// This and the other sibling blind schedules come in two pieces. The first piece dictates "when" blinds are increased.
// The second (the BlindPolicy) dictates "how" blinds are increased (e.g. doubled / from a file)

public class FixedHandBlindSchedule implements BlindSchedule {
  
  private int num_hands;
  private int current_hand;

  private Blind current;
  private BlindPolicy policy;
  /**
   * Creates a blind schedule that doubles every thirty hands; With initial blinds of 20/10/5
   */
  public FixedHandBlindSchedule(){
    this.num_hands = 30;
    this.current_hand = 0;
    
    this.current = null;
    this.policy = new DoubleBlindPolicy(new Blind(20,10,5));
  }
  
  /**
   * @param num_hands The number of hands before the blinds increase
   */
  public FixedHandBlindSchedule(int num_hands, BlindPolicy policy){
    this.num_hands = num_hands;
    this.current_hand = 0;
    
    this.current = null;
    
    this.policy = policy;
  }

  /**
   * Get the blinds for the next round of play. 
   * 
   * The number of calls to getBlinds affects the result!
   * It should only be called once per hand.
   * 
   * @param num_players the current number of players at the table
   */
  @Override
  public Blind getBlinds(int num_players) {
    if(this.current_hand == 0)
      this.current = this.policy.increase();
    
    this.current_hand += 1;
    this.current_hand %= this.num_hands;
    return current;
  }

}
