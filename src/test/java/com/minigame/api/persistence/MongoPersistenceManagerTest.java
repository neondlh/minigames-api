package com.minigame.api.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.minigame.api.model.Game;
import com.minigame.api.model.GameType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-persistence-configuration.xml")
public class MongoPersistenceManagerTest {
	@Autowired
	private MongoPersistenceManager persistenceManager;

	@Test
	public void testSave() {
		Game game = new Game(GameType.QUIZ, new String[] { "playerOne" });
		persistenceManager.saveGame(game);
		Set<Game> games = persistenceManager.getGames();
		assertNotNull(games.iterator().next());
		assertEquals(new Double(0), games.iterator().next().getScores().get("playerOne"));
	}

}
