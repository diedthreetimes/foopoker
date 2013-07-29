package com.sprout.foopoker.gamelogic;

/**
 * Implement this class to define a method for increasing blinds.
 * 
 * After every hand getBlinds is called.
 * @author sjfaber
 *
 */
public interface BlindSchedule {
  public Blind getBlinds(int num_players);
}
