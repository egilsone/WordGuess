package org.example.wordguess.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.wordguess.game.GameFactory;
import com.example.wordguess.interfaces.Game;

public class GameTest {

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testThatGameIsNotFinished() {
		Game game = GameFactory.createSimpleGame(5,3);
		assertEquals(false, game.isFinished());
		assertTrue(game.percentageResult() <= 0.0);
	}

	

	
}
