package com.example.wordguess.interfaces;

import java.util.List;


public interface Question {

	public String getQuestion();
	
	public List<Answer> getPossibleAnswers();
	
	public Answer getUserAnswer();
	
	public void setUserAnswer(Answer answer);
	
	public boolean isAnsweredCorrectly();

	public boolean isAnswered();

	public Answer getCorrectAnswer();
	
	public void setCorrectAnswer(Answer correctAnswer);
	
}
