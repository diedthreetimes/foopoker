package com.sprout.foopoker.gamelogic;

/**
 * This represents big/small blind as well as any antes
 * @author sjfaber
 *
 */
// I'm thinking that this class will keep track of the types of blind increases (maybe read in from a file)
//   I'm not particularrly happy with that design though...

public class Blind implements Cloneable{
  private int big;
  private int small;
  private int ante;

  public Blind(int big, int small, int ante){
    this.big = big;
    this.small = small;
    this.ante = ante;
  }
  

  public int getBig(){
    return this.big;
  }
  public int getSmall(){
    return this.small;
  }
  public int getAnte(){
    return this.ante;
  }
  
  public Blind clone(){
    return new Blind(this.big, this.small, this.ante);
  }
  
  public boolean equals(Blind other){
    return this.big == other.big && this.small == other.small && this.ante == other.ante;
  }
}
