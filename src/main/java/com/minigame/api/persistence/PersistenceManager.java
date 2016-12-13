package com.minigame.api.persistence;

import java.util.Set;

import com.minigame.api.model.Game;
import com.minigame.api.model.quiz.Question;

public interface PersistenceManager {
	public Set<Game> getGames();

	public void saveGame(Game game);

	public Set<Question> getQuestions();

	public void saveQuestion(Question question);
}
