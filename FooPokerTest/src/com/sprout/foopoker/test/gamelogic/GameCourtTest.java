package com.sprout.foopoker.test.gamelogic;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.sprout.foopoker.gamelogic.Card;
import com.sprout.foopoker.gamelogic.GameCourt;
import com.sprout.foopoker.gamelogic.Hand;
import com.sprout.foopoker.gamelogic.Player;

public class GameCourtTest extends TestCase{

  Player p1 = new Player(1, 100);
  Player p2 = new Player(2, 100);
  Player p3 = new Player(3, 100);
  
  ArrayList<Card> fiveCards1 = new ArrayList<Card>(); // 2 - 3 - J - J - 10
  ArrayList<Card> fiveCards2 = new ArrayList<Card>();
  ArrayList<Card> fiveCards3 = new ArrayList<Card>();
  ArrayList<Card> fiveCards4 = new ArrayList<Card>();
  ArrayList<Card> fiveCards5 = new ArrayList<Card>();
  
  ArrayList<Player> players1 = new ArrayList<Player>();
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    ClassifierTest hands = new ClassifierTest();
    hands.setUp();
    fiveCards1.add(new Card(2));
    fiveCards1.add(new Card(3));
    fiveCards1.add(new Card(11));
    fiveCards1.add(new Card(24));
    fiveCards1.add(new Card(49));
    
    Hand h1 = new Hand();
    h1.appendCard(new Card(15));
    h1.appendCard(new Card(16));
    p1.setHand(h1);
    
    Hand h2 = new Hand();
    h2.appendCard(new Card(1));
    h2.appendCard(new Card(13));
    p2.setHand(h2);
    
    Hand h3 = new Hand();
    h3.appendCard(new Card(28));
    h3.appendCard(new Card(27));
    p3.setHand(h3);
    
    players1.add(p1);
    players1.add(p2);
    players1.add(p3);
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testMain() {
    GameCourt gc = new GameCourt(fiveCards1, players1);
  }
}
