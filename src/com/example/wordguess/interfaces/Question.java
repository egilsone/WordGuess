package com.example.wordguess.interfaces;

import java.util.List;


public interface Question {

	public String getQuestion();
	
	public List<Answer> getPossibleAnswers();
	
	public Answer getCorrectAnswer();
	
	public void setUserAnswer(Answer answer);
	
	public Answer getUserAnswer();
	
	public boolean isAnsweredCorrectly();

	public boolean isAnswered();

	public void setCorrectAnswer(Answer correctAnswer);
	
}
