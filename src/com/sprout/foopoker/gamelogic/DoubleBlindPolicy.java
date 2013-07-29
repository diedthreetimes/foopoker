package com.sprout.foopoker.gamelogic;

/**
 * With this policy the blinds and antes double every interval.
 * @author sjfaber
 *
 */
public class DoubleBlindPolicy implements BlindPolicy {

  private Blind cur;
  /**
   * 
   * @param initial blind.
   */
  public DoubleBlindPolicy(Blind initial){
    cur = initial;
  }
  @Override
  public Blind increase() {
    Blind tmp = cur;
    cur =  new Blind(tmp.getBig()*2, tmp.getSmall()*2, tmp.getAnte()*2);
    
    return tmp;
  }

}
