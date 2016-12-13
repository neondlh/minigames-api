package com.minigame.api.endpoint;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.minigame.api.model.quiz.Question;

@RequestMapping("/questions")
public class QuestionEndPointController extends EndPointController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		getPersistenceManager().saveQuestion(question);
		return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<Question>> getQuestions(@RequestParam(value = "numberOfQuestionsToReturn", defaultValue = "0") int numberOfQuestionsToReturn) {
		Set<Question> questions = getPersistenceManager().getQuestions();
		Set<Question> questionsToReturn = Sets.newHashSet();

		if (numberOfQuestionsToReturn != 0) {
			Set<Integer> questionIndexes = createQuestionIndexes(numberOfQuestionsToReturn, questions);
			for (Integer questionIndex : questionIndexes) {
				questionsToReturn.add(Iterables.get(questions, questionIndex));
			}
		} else {
			questionsToReturn = questions;
		}
		return new ResponseEntity<Set<Question>>(questionsToReturn, HttpStatus.OK);
	}

	private Set<Integer> createQuestionIndexes(int numberOfQuestionsToReturn, Set<Question> questions) {
		Set<Integer> questionIndexes = Sets.newHashSet();
		while (questionIndexes.size() < numberOfQuestionsToReturn) {
			Integer next = ThreadLocalRandom.current().nextInt(questions.size()) + 1;
			questionIndexes.add(next);
		}
		return questionIndexes;
	}

}
