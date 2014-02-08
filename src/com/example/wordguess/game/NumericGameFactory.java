package com.example.wordguess.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.wordguess.interfaces.Answer;
import com.example.wordguess.interfaces.Game;
import com.example.wordguess.interfaces.NumericQuestionFactory;
import com.example.wordguess.interfaces.Question;

public class NumericGameFactory {

	public static Game createSimpleGame(int numberOfQuestions, int numberOfAlternatives, NumericQuestionFactory nqf) {
		return createSimpleGame(numberOfQuestions, numberOfAlternatives, new GameRange(0,9,0,9), nqf);
	}
	
	public static Game createSimpleGame(int numberOfQuestions, int numberOfAlternatives, GameRange termRange, NumericQuestionFactory nqf) {
		return createSimpleGame( new GameRange(0,numberOfQuestions,0,numberOfAlternatives), termRange, nqf);
	}

	private static Game createSimpleGame( GameRange questionRange, GameRange termRange, NumericQuestionFactory nqf ) {
		List<Integer> terms1 = createTermList(termRange.getMinX(), termRange.getMaxX());
		List<Integer> terms2 = createTermList(termRange.getMinY(), termRange.getMaxY());
		int numberOfQuestions    = questionRange.getMaxX() - questionRange.getMinX();
		int numberOfAlternatives = questionRange.getMaxY() - questionRange.getMinY();

		Random rnd = new Random(System.currentTimeMillis());
		
		GameImpl result = new GameImpl();
		List<Question> questions = new ArrayList<Question>();
		for( int i=1; i<=numberOfQuestions; i++ ) {  // Antal frågor
			int index1 = rnd.nextInt(terms1.size());
			int index2 = rnd.nextInt(terms2.size());
			int term1 = terms1.get(index1).intValue();
			int term2 = terms2.get(index2).intValue();

			Question question = new QuestionImpl( nqf.getNewQuestion(term1, term2) );
			List<Answer> answers = new ArrayList<Answer>();
			Answer correctAnswerImpl = new AnswerImpl(nqf.getCorrectAnswer(term1, term2), true);
			answers.add(correctAnswerImpl);
			for( int j=1; j<=numberOfAlternatives-1; j++ ) { 
				// Antal svar förutom det korrekta
				answers.add(new AnswerImpl(nqf.getIncorrectAlternative(term1, term2, j), false));
			}
			Collections.shuffle(answers); // Blanda
			((QuestionImpl)question).setAnswers(answers);
			question.setCorrectAnswer(correctAnswerImpl); //TODO Could be found from question
			questions.add(question);
		}
		result.setQuestions(questions);
		return result;
	}
	
	/**
	 * Creates a list with all the values from minTerm to maxTerm (inclusive)
	 * @param minTerm
	 * @param maxTerm
	 * @return a list with all the Integer's (empty if maxTerm < minTerm)
	 */
	private static List<Integer> createTermList(int minTerm, int maxTerm) {
		List<Integer> result = new ArrayList<Integer>();
		for( int i=minTerm; i<=maxTerm; i++) {
			result.add( Integer.valueOf(i) );
		}
		return result;
	}
	
}





