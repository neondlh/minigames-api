package com.minigame.api.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
public class EntryEndPointController extends EndPointController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> home() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}
}
