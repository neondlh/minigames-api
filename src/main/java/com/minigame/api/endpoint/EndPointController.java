package com.minigame.api.endpoint;

import org.springframework.stereotype.Controller;

import com.minigame.api.persistence.PersistenceManager;

@Controller
public class EndPointController {

	private PersistenceManager persistenceManager;

	public PersistenceManager getPersistenceManager() {
		return persistenceManager;
	}

	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
}
