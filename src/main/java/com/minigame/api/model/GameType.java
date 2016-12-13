package com.minigame.api.model;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public enum GameType {

	QUIZ("quiz"), RACE("race");

	private String type;

	GameType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static GameType getRandomType() {
		int indexType = ThreadLocalRandom.current().nextInt(0, values().length + 1);
		return values()[indexType];
	}

}
