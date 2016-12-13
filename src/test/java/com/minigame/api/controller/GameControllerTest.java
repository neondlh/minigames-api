package com.minigame.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.minigame.api.model.Game;
import com.minigame.api.model.GameType;

public class GameControllerTest {
	private GameController gameController;

	@Before
	public void newRandomGameOnePlayer() {
		gameController = new GameController();
	}

	@Test
	public void newQuizGameOnePlayer() {
		Game newQuiz = gameController.getNewGame(GameType.QUIZ, new String[] { "playerOne" });
		assertNotNull(newQuiz);
		assertNotNull(newQuiz.getScores());
		assertEquals(new Double(0), newQuiz.getScores().get("playerOne"));
		assertNotNull(newQuiz.getPlayerNames());
		assertEquals("playerOne", newQuiz.getPlayerNames()[0]);
	}

	@Test
	public void newQuizGameTwoPlayers() {
		Game newQuiz = gameController.getNewGame(GameType.QUIZ, new String[] { "playerOne", "playerTwo" });
		assertNotNull(newQuiz);
		assertNotNull(newQuiz.getScores());
		assertEquals(new Double(0), newQuiz.getScores().get("playerOne"));
		assertEquals(new Double(0), newQuiz.getScores().get("playerTwo"));
		assertNotNull(newQuiz.getPlayerNames());
		assertEquals("playerOne", newQuiz.getPlayerNames()[0]);
		assertEquals("playerTwo", newQuiz.getPlayerNames()[1]);
	}

}
