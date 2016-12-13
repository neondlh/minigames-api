package com.minigame.api.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.minigame.api.model.Game;
import com.minigame.api.model.GameType;

public class InMemoryPersistenceManagerTest {
	private InMemoryPersistenceManager filePersisntenceManager;

	@Before
	public void setUp() {
		filePersisntenceManager = new InMemoryPersistenceManager();
	}

	@Test
	public void getGamesTest() {
		Set<Game> games = filePersisntenceManager.getGames();
		assertNotNull(games);
	}

	@Test
	public void saveGameTest() {
		Game newGame = new Game(GameType.QUIZ, new String[] { "playerOne" });

		Set<Game> games = filePersisntenceManager.getGames();
		assertNotNull(games);
		int numberOfGamesBeforeSave = games.size();

		filePersisntenceManager.saveGame(newGame);

		Set<Game> gamesAfterSave = filePersisntenceManager.getGames();
		assertNotNull(gamesAfterSave);
		int numberOfGamesAfterSave = gamesAfterSave.size();

		assertTrue(numberOfGamesBeforeSave < numberOfGamesAfterSave);
	}

}
