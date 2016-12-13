package com.minigame.api.endpoint;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minigame.api.model.Game;
import com.minigame.api.model.GameType;

@RequestMapping("/games")
public class GameEndPointController extends EndPointController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<Game>> getGames() {
		Set<Game> games = getPersistenceManager().getGames();
		return new ResponseEntity<Set<Game>>(games, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Game> createGame(@RequestParam(name = "gameType", required = false) GameType gameType,
			@RequestParam(value = "playerNames", defaultValue = "Uknown") String... playerNames) {
		Game newGame = null;
		if (gameType == null) {
			newGame = new Game(GameType.getRandomType(), playerNames);
		} else {
			newGame = new Game(gameType, playerNames);
		}
		getPersistenceManager().saveGame(newGame);
		return new ResponseEntity<Game>(newGame, HttpStatus.CREATED);
	}

}
