package com.sprout.foopoker.test.gamelogic;
 
import junit.framework.TestCase;

import com.sprout.foopoker.gamelogic.Table;
import com.sprout.foopoker.test.mock.MockPlayer;
import java.util.*;

// TODO: For now either of these print to the system log
//   However, the system log is not captured by the LogViewerFragment
//   Make an issue about this on github when internet is available
// Log.d("TAG", "This doesn't work either");
// System.out.println("About to run i: "+i);

public class TableTest extends TestCase
{
	public void test_construct(){
        Table table = new Table();
        assertEquals(table.numActive(), 0);
    }
    
    public void test_numActive(){
        Table table = new Table();
       
        for (int i=0; i < 10; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
            assertEquals(i+1, table.numActive());
        }
       
        for (int i=0; i < 10; i++){
            table.next();
            table.foldCurrent();
            assertEquals(9-i, table.numActive());
        }
       
        // repeat above with less than 10 players
        table = new Table();

        for (int i=0; i < 5; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
            assertEquals(i+1, table.numActive());
        }

        for (int i=0; i < 5; i++){
            table.next();
            table.foldCurrent();
            assertEquals(4-i, table.numActive());
        }
    }
    
    // This also tests next round
    public void test_next(){
        Table table = new Table();
        for (int i=0; i < 5; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
        }
        
        // Without advancing to the next round cur starts at the dealer
        assertEquals(0, table.peek().getId());
        for (int i=1; i < 5; i++){
            assertEquals(i, table.next().getId());
        }
        assertEquals(0, table.next().getId());
        
        // After cur starts just after the delaer
        table.nextRound();
        assertEquals(3, table.peek().getId());
        for (int i=4; i < 5; i++){
            assertEquals(i, table.next().getId());
        }
        for (int i=0; i < 4; i++){
            assertEquals(i, table.next().getId());
        }
        
        table.nextRound();
        for (int i=4; i < 5; i++){
            assertEquals(i, table.next().getId());
        }
        for (int i=0; i < 4; i++){
            assertEquals(i, table.next().getId());
        }
        
		// test that next doesn't loop forever
		for (int i=0; i < table.size(); i++) {
			table.next();
			table.foldCurrent();
		}
		table.next();
    }
    
    public void test_get(){
        Table table = new Table();
        
        // Basic test
        for (int i=0; i < 8; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
        }
        for (int i=0; i < 8; i++){
            assertEquals(i, table.get(i).getId());
        }
        
        // Test with holes (some players leave)
        table = new Table();
        for (int i=0; i < 8; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
        }
        table.remove(7); // Last player
        table.remove(5);
        table.remove(2);
		
        int[] expected_ids = {0,1,3,4,6};
        for (int j=0; j < 5; j++){
            assertEquals(expected_ids[j], table.get(j).getId());
        }
    }
	
	// Test remove (make sure that things shift as expected)
    //  this differs fromt he previous example (with holes) in that
    //  we should test removing in a non decreasing order
    public void test_remove(){
        Table table = new Table();

        // Basic test
        for (int i=0; i < 8; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
        }
        
        // 0 1 2 3 4 5 6 7
        table.remove(2);
        // 0 1 - 3 4 5 6 7
        assertEquals(3, table.get(2).getId());
        table.remove(4);
        // 0 1 - 3 4 - 6 7
        assertEquals(7, table.get(5).getId());
        table.remove(0);
        // - 1 - 3 4 - 6 7
        assertEquals(1, table.get(0).getId());
        table.remove(2);
        // - 1 - 3 - - 6 7
        assertEquals(7, table.get(3).getId());
        table.remove(1);
        // - 1 - - - - 6 7
        assertEquals(6, table.get(1).getId());
        table.remove(0);
        // - - - - - - 6 7
        assertEquals(7, table.get(1).getId());
        table.remove(0);
        assertEquals(7, table.get(0).getId());
        table.remove(0);
        
        assertEquals(null, table.get(0));
    }
    
    public void test_size(){
        Table table = new Table();
        for (int i=0; i < 10; i++){
            MockPlayer p = new MockPlayer(i, 1000);
            table.insert(p);
            assertEquals(i+1, table.size());
        }
    }
    
    // TODO: test method behaviour when table is empty
	// TODO: test removeBroke
}
