package com.example.wordguess.game;

import java.util.ArrayList;
import java.util.List;

import com.example.wordguess.interfaces.Answer;
import com.example.wordguess.interfaces.Question;

public class QuestionImpl implements Question {

	private String question;
	private Answer correctAnswer;
	private Answer userAnswer;
	private List<Answer> answers = new ArrayList<Answer>();
	
	public QuestionImpl(String question) {
		this.question = question;
	}
	
	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public List<Answer> getPossibleAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public Answer getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public void setUserAnswer(Answer answer) {
		this.userAnswer = answer;
	}
	
	@Override
	public Answer getUserAnswer() {
		return userAnswer;
	}

	@Override
	public boolean isAnsweredCorrectly() {
		return userAnswer != null && userAnswer.isCorrect();
	}

	@Override
	public boolean isAnswered() {
		return userAnswer != null;
	}

	@Override
	public String toString() {
		return "Question: " + question + " Answers: " + answers + " Correct: " + correctAnswer;
	}

	@Override
	public void setCorrectAnswer(Answer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
