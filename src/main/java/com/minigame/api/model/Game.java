package com.minigame.api.model;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Arrays;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Game {
	private long id;

	private GameType gameType;

	private String[] playerNames;

	private Map<String, Double> scores;

	public Game(GameType gameType, String[] playerNames) {
		this.gameType = gameType;
		this.playerNames = playerNames;
		this.scores = newHashMap();
		for (String playerName : playerNames) {
			scores.put(playerName, new Double(0));
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public String[] getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(String[] playerNames) {
		this.playerNames = playerNames;
	}

	public Map<String, Double> getScores() {
		return scores;
	}

	public void setScores(Map<String, Double> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameType=" + gameType + ", playerNames=" + Arrays.toString(playerNames) + ", scores=" + scores + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
