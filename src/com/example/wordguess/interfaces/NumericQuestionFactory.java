package com.example.wordguess.interfaces;

public interface NumericQuestionFactory {

	/**
	 * Generates a new question with the specified terms
	 * @param term1
	 * @param term2
	 * @return the question as a string
	 */
	public String getNewQuestion(int term1, int term2);

	/**
	 * Returns the string representation of the two terms, these should NOT be correct
	 * 
	 * @param term1
	 * @param term2
	 * @param alternativeNumber - gives a hint of which number
	 * @return the string representation of the specified terms
	 */
	public String getIncorrectAlternative(int term1, int term2, int alternativeNumber);

	/**
	 * Returns the correct answer to the two terms
	 * @param term1
	 * @param term2
	 * @return a string representation of the correct answer
	 */
	public String getCorrectAnswer(int term1, int term2);
	
}
