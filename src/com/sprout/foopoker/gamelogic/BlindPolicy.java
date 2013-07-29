package com.sprout.foopoker.gamelogic;

public interface BlindPolicy {
  /**
   * Increase the blinds one level. Must be called once to get the starting blind
   * @return The new blind
   */
  Blind increase();
}
