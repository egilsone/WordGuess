package com.example.wordguess.game;

import com.example.wordguess.interfaces.Answer;

public class AnswerImpl implements Answer {
	private String answer;
	private boolean isCorrect = false;
	
	public AnswerImpl(String answer, boolean isCorrect) {
		this.answer = answer;
		this.isCorrect = isCorrect;
	}
	
	@Override
	public String getAnswer() {
		return answer;
	}

	@Override
	public boolean isCorrect() {
		return isCorrect;
	}

	@Override
	public String toString() {
		return "AnswerImpl: " + answer;
	}
	
}
