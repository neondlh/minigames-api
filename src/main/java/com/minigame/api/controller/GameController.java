package com.minigame.api.controller;

import com.minigame.api.model.Game;
import com.minigame.api.model.GameType;
import com.minigame.api.persistence.PersistenceManager;

public class GameController {

	private PersistenceManager persistenceManager;

	public Game getNewGame(GameType gameType, String[] playerNames) {
		return new Game(gameType, playerNames);
	}

	public PersistenceManager getPersistenceManager() {
		return persistenceManager;
	}

	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

}
