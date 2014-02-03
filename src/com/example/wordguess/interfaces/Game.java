package com.example.wordguess.interfaces;

import java.util.List;

public interface Game {

	public boolean isFinished();

	public int getNumberOfCorrectAnswers();

	public double percentageResult();

	public List<Question> getQuestions();
	
}