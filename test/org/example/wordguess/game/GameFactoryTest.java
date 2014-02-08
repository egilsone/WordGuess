package org.example.wordguess.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.wordguess.game.NumericGameFactory;
import com.example.wordguess.game.NumericQuestionFactoryFactory;
import com.example.wordguess.interfaces.Answer;
import com.example.wordguess.interfaces.Game;
import com.example.wordguess.interfaces.Question;

public class GameFactoryTest {

	private Game game = null;
	private List<Answer> allAnswers = new ArrayList<Answer>();
	private List<Answer> correctAnswers = new ArrayList<Answer>();
	
	@Before
	public void setUp() {
		game = NumericGameFactory.createSimpleGame(3, 4, NumericQuestionFactoryFactory.getQuestionFactory("/"));
		for( Question q : game.getQuestions() ) {
			allAnswers.addAll(q.getPossibleAnswers());
		}
		for( Answer a : allAnswers ) {
			if( a.isCorrect() ) {
				correctAnswers.add(a);
			}
		}
	}
	
	@Test
	public void testThatGameIsNotFinished() {
		assertEquals(false, game.isFinished());
		assertTrue(game.percentageResult() <= 0.0);
	}

	@Test
	public void testGameQuestionAnswerGeneration() {
		// Check that we have generated all questions
		assertEquals(12, allAnswers.size());
	}

	@Test
	public void testGameQuestionAnswerPrint() {
		// Check that we have generated all questions
		for( Question q : game.getQuestions() ) {
			System.out.println(q);
		}
		for( Answer a : allAnswers ) {
			System.out.println(a);
		}
	}
	
	@Test 
	public void checkThatAnswersAreMarkedCorrectly() {
		// Check that we only have 4 correct answers (checks that we have set initialized the Answer with (correct=true)
		assertEquals(3, correctAnswers.size());
		for( int i=0; i<3; i++ ) {
			Question q = game.getQuestions().get(i);
			// Checks that we have set the question.setCorrectAnswer()
			assertEquals(q.getCorrectAnswer(), correctAnswers.get(i));
		}
	}
	
}
