package com.sprout.foopoker.gamelogic;

public class Player{

	
	public long stack;
	public Status status;
	public enum Status { PLAYING, FOLDED, FINISH }
	public User user;
	
	private Card[] hand;
	
	public Player(int id, long stack) {
		this.user = new User(id);
		if (stack > 0 && stack <= user.totalStack)
			this.stack = stack;
		else
			throw new IllegalArgumentException(stack + " is not legal");
		this.hand = new Card[2];
		this.status = Status.PLAYING;
	}
	
	public void bet(long value) {
		stack -= value;
	}
	
	public void winPot(long value) {
		stack += value;
	}
	
	/**
	 * 
	 * @return hand of the player. I do check for the status
	 */
	public Card[] showCards() {
		if (status.equals(Status.FOLDED)) {
			if (user.allowHistory)
				return hand;
			else
				return null;
		} 
		// TODO: we have to be sure that game is finished
		else if (status.equals(Status.FINISH)){
			return hand;
		}
		// TODO: I know this looks weird:)
		else if (status.equals(Status.PLAYING))
			return null;
		return null;
		
	}
	
	public void foldHand() {
		status = Status.FOLDED;
	}
	
	public void finishHand() {
		status = Status.FINISH;
	}
	
	// TODO: copy constructor
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
