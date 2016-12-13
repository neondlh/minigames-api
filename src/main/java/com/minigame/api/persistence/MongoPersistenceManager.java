package com.minigame.api.persistence;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.google.common.collect.Sets;
import com.minigame.api.model.Game;
import com.minigame.api.model.quiz.Question;

public class MongoPersistenceManager implements PersistenceManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoPersistenceManager.class);

	private MongoTemplate mongoTemplate;

	@Override
	public Set<Game> getGames() {
		List<Game> games = mongoTemplate.findAll(Game.class, "Games");
		return Sets.newHashSet(games);
	}

	@Override
	public void saveGame(Game game) {
		LOGGER.debug("Saving game: " + game);
		mongoTemplate.save(game, "Games");
	}

	@Override
	public Set<Question> getQuestions() {
		List<Question> questions = mongoTemplate.findAll(Question.class, "Questions");
		return Sets.newHashSet(questions);
	}

	@Override
	public void saveQuestion(Question question) {
		LOGGER.debug("Saving question: " + question);
		mongoTemplate.save(question, "Questions");
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
