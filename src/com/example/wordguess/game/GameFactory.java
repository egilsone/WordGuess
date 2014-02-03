package com.example.wordguess.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.wordguess.interfaces.Answer;
import com.example.wordguess.interfaces.Game;
import com.example.wordguess.interfaces.Question;

public class GameFactory {
	public static Game createSimpleGame(int numberOfQuestions, int numberOfAlternatives) {
		int[] terms = { 0,1,2,3,4,5,6,7,8,9};
		Random rnd = new Random(System.currentTimeMillis());
		
		GameImpl result = new GameImpl();
		List<Question> questions = new ArrayList<Question>();
		for( int i=1; i<=numberOfQuestions; i++ ) {  // Antal frågor
			int index1 = rnd.nextInt(terms.length);
			int index2 = rnd.nextInt(terms.length);
			int term1 = terms[index1];
			int term2 = terms[index2];
			int correctAnswer = term1 + term2;
			
			Question question = new QuestionImpl( term1 + " + " + term2 + " = " );
			List<Answer> answers = new ArrayList<Answer>();
			Answer correctAnswerImpl = new AnswerImpl(""+correctAnswer, true);
			answers.add(correctAnswerImpl);
			for( int j=1; j<=numberOfAlternatives-1; j++ ) { // Antal svar förutom det korrekta
				answers.add(new AnswerImpl("" + (correctAnswer+j), false));
			}
			Collections.shuffle(answers); // Blanda
			((QuestionImpl)question).setAnswers(answers);
			question.setCorrectAnswer(correctAnswerImpl);
			questions.add(question);
		}
		result.setQuestions(questions);
		return result;
	}
}





