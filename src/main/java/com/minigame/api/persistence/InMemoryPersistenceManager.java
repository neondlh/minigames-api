package com.minigame.api.persistence;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.minigame.api.model.Game;
import com.minigame.api.model.quiz.Question;

public class InMemoryPersistenceManager implements PersistenceManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryPersistenceManager.class);

	private static final String DB_QUESTIONS_FILE = "db/questions.json";

	private static final String DB_GAMES_FILE = "db/games.json";

	private static ObjectMapper MAPPER = new ObjectMapper();

	private Set<Game> games;

	private Set<Question> questions;

	@Override
	public Set<Game> getGames() {
		if (CollectionUtils.isEmpty(games)) {
			games = getListFromFile(DB_GAMES_FILE);
			LOGGER.debug(String.format("games from file [%s]", games));
		}
		return games;
	}

	@Override
	public void saveGame(Game game) {
		Set<Game> games = getGames();
		LOGGER.debug(String.format("saving new game [%s]", game));
		games.add(game);
	}

	@Override
	public Set<Question> getQuestions() {
		if (CollectionUtils.isEmpty(questions)) {
			questions = getListFromFile(DB_QUESTIONS_FILE);
			LOGGER.debug(String.format("questions from file [%s]", questions));
		}
		return questions;
	}

	@Override
	public void saveQuestion(Question question) {
		Set<Question> questions = getQuestions();
		LOGGER.debug(String.format("saving new question [%s]", question));
		questions.add(question);
	}

	@SuppressWarnings("unchecked")
	private <T> Set<T> getListFromFile(String fileName) {
		Set<T> newList = Sets.newHashSet();
		try {
			newList = MAPPER.readValue(getFileAsString(fileName), Set.class);
		} catch (Exception e) {
			LOGGER.error(String.format("error parsing list from %s to List of %s", fileName, newList.getClass()));
			throw new RuntimeException(String.format("error parsing list from %s to List of %s", fileName, newList.getClass()), e);
		}
		return newList;
	}

	private String getFileAsString(String filePath) {
		String fileAsString = new String();
		try {
			fileAsString = IOUtils.toString(getFileAsStream(filePath), StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOGGER.error("error reading file: " + filePath);
			throw new RuntimeException("error reading file: " + filePath, e);
		}
		return fileAsString;

	}

	private InputStream getFileAsStream(String filePath) throws URISyntaxException {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
	}

}
