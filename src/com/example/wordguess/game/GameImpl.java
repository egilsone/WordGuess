package com.example.wordguess.game;

import java.util.List;

import com.example.wordguess.interfaces.Game;
import com.example.wordguess.interfaces.Question;

public class GameImpl implements Game {
	
	List<Question> questions = null;
	
	/* (non-Javadoc)
	 * @see com.example.wordguess.game.Game#evaluate()
	 */
	public GameImpl() {
		// empty default constructor
	}

	@Override
	public String toString() {
		return "Game" + questions.toString();
	}
	
	public void setQuestions( List<Question> questions ) {
		this.questions = questions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	/* (non-Javadoc)
	 * @see com.example.wordguess.game.Game#isFinished()
	 */
	@Override
	public boolean isFinished() {
		boolean result = true;
		for( Question q : questions ) {
			if( !q.isAnswered() ) {
				return false;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.example.wordguess.game.Game#getNumberOfCorrectAnswers()
	 */
	@Override
	public int getNumberOfCorrectAnswers() {
		int result = 0;
		for( Question q : questions ) {
			if( q.isAnsweredCorrectly() ) {
				result++; 
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.example.wordguess.game.Game#percentageResult()
	 */
	@Override
	public double percentageResult() {
		if( questions.size() == 0 ) {
			return 0.0;
		} else {
			return getNumberOfCorrectAnswers() / questions.size();
		}
	}
	
}



