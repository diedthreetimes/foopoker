package com.sprout.foopoker.test.gamelogic;

import java.util.*;

import com.sprout.foopoker.gamelogic.*;

import junit.framework.TestCase;
import com.sprout.foopoker.test.mock.*;
import com.sprout.foopoker.test.mock.MockHand;

public class GameHandTest extends TestCase {

	private GameHand gh;
	private MockHand mh;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		
		List<Player> mPlayers = new ArrayList<Player>();
		
		Table table = new Table();
		
		// Test with a 5 player game
		for(int i=0; i < 5; i++){
			MockPlayer p = new MockPlayer(i, 1000L);
			table.insert(p);
			mPlayers.add(p);
		}
		
		MockDealer dl = new MockDealer();
		gh = new GameHand(table, new Blind(100, 50, 25), dl);
		
		mh = new MockHand(mPlayers, dl, gh);
	}
	
	
	public void test_play(){
		// todo: comr up with some dsl for a transcript of a game
	}
	
	public void test_getPot(){
		
	}
	
	public void test_getWinners(){
		
	}
	
	public void test_getCards(){
		
	}
	
	public void test_saveHand(){
		
	}
	
	
	// We may not need to test the below private methods if we do
	// Method method = targetClass.getDeclaredMethod(methodName, argClasses);
	// method.setAccessible(true);
	// return method.invoke(targetObject, argObjects
	/*public void test_placeBet(){
		
	}
	
	public void test_dealShared(){
		
	}
	
	public void test_startNewHand(){
		
	}*/
	
	
}
