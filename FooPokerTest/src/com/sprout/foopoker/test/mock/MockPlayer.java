package com.sprout.foopoker.test.mock;

import com.sprout.foopoker.gamelogic.Player;
import android.util.Pair;
import java.util.Stack;

public class MockPlayer extends Player
{

	Stack<Pair<Action,Integer>> actions;
	Stack<Pair<Action,Integer>> defaultActions;
	
	public MockPlayer(int id,long stack){
		super(id,stack);
		
		actions = new Stack<Pair<Action,Integer>>();
		defaultActions = new Stack<Pair<Action,Integer>>();
	}
	
	public void defaultMinimumRaise(){
		defaultActions.clear();
		defaultActions.add(new Pair<Action,Integer>(Player.Action.RAISE, 1));
	}
	
	// For ultimate flexibility could do something along the lines of
	// public void defaultAction(ActionCallback callback){
	// }
	// However, one could also just as easily extend MockPlayer
	
	public void defaultFold(){
		defaultActions.clear();
		defaultActions.add(new Pair<Action,Integer>(Player.Action.FOLD, 0));
	}
	
	public void defaultCall(){
		defaultActions.clear();
		defaultActions.add(new Pair<Action,Integer>(Player.Action.CALL, 0));
	}
	
	//TODO: allow adding to the default actions
	
	/**
	 * Add the action to perform in a slightly different from then returned by play.
	 *   All bets are in terms of the minimum_raise.
	 */
	public void addAction(Pair<Action,Integer> action){
		actions.add(action);
	}
	
	@Override
	public Pair<Action,Integer> play(int additional_bet, int minimum_raise) {
		
		if(actions.empty()) {
			actions.addAll(defaultActions);			
		}
					
		return actions.pop();
	}
}
