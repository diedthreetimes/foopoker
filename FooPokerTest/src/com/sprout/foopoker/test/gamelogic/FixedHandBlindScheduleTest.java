/**
 * 
 */
package com.sprout.foopoker.test.gamelogic;

import com.sprout.foopoker.gamelogic.Blind;
import com.sprout.foopoker.gamelogic.BlindSchedule;
import com.sprout.foopoker.gamelogic.DoubleBlindPolicy;
import com.sprout.foopoker.gamelogic.FixedHandBlindSchedule;

import junit.framework.TestCase;

/**
 * @author sjfaber
 *
 */
public class FixedHandBlindScheduleTest extends TestCase {
  
  public void testUpdateInterval(){
    BlindSchedule bs = new FixedHandBlindSchedule(5, new DoubleBlindPolicy(new Blind(20,10,0)));

    Blind tmp = bs.getBlinds(10);
    Blind tmp1 = null;
    for(int j=0; j < 6; j++){
      for(int i=1; i < 5; i++){
        // Each of these should be the same
        assertEquals( bs.getBlinds(10),tmp );
      }
      tmp1 = bs.getBlinds(10);
      assertEquals(tmp1.getBig(), tmp.getBig()*2);
      tmp = tmp1;
    }
  }

}
