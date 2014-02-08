package com.example.wordguess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.wordguess.interfaces.NumericQuestionFactory;

public class NumericQuestionFactoryFactory {

	private static List<NumericQuestionFactory> factories = new ArrayList<NumericQuestionFactory>();
	
	private static synchronized void initialize() 
	{
		if( factories.size() == 0 ) {
			factories.add(new PlusQuestionFactory());
			factories.add(new MinusQuestionFactory());
			factories.add(new MultiplyQuestionFactory());
			factories.add(new DivideQuestionFactory());
		}
	}
	
	public static NumericQuestionFactory getQuestionFactory(String sign) {
		initialize();
		return factories.get(convert(sign));
	}
	
	private static int convert(String sign) {
		if( sign != null ) {
			sign = sign.trim();
		}
		if( sign.equals("+") ) {
			return 0;
		} else if( sign.equals("-") ) {
			return 1;
		} else if( sign.equals("*") ) {
			return 2;
		} else if( sign.equals("/") ) {
			return 3;
		} else if( sign.equals("?") ) {
			Random rnd = new Random(System.currentTimeMillis());
			return rnd.nextInt(factories.size());
		} else {
			throw new IllegalArgumentException("Invalid value - only +,-,*,/,? allowed");
		}
	}

	private static class PlusQuestionFactory implements NumericQuestionFactory {
		@Override
		public String getNewQuestion(int term1, int term2) {
			return term1 + " + " + term2 + " = ";
		}
	
		@Override
		public String getIncorrectAlternative(int term1, int term2, int alternativeNumber) {
			if( alternativeNumber % 2 == 0 ) {
				return "" + (term1 + term2 + alternativeNumber);
			} else {
				return "" + (term1 + term2 - alternativeNumber);
			}
		}
	
		@Override
		public String getCorrectAnswer(int term1, int term2) {
			return "" + (term1 + term2);
		}
	
	}
	private static class MinusQuestionFactory implements NumericQuestionFactory {
		@Override
		public String getNewQuestion(int term1, int term2) {
			return term1 + " - " + term2 + " = ";
		}
	
		@Override
		public String getIncorrectAlternative(int term1, int term2, int alternativeNumber) {
			if( alternativeNumber % 2 == 0 ) {
				return "" + (term1 - term2 + alternativeNumber);
			} else {
				return "" + (term1 - term2 - alternativeNumber);
			}
		}
	
		@Override
		public String getCorrectAnswer(int term1, int term2) {
			return "" + (term1 - term2);
		}
	
	}
	private static class MultiplyQuestionFactory implements NumericQuestionFactory {
		@Override
		public String getNewQuestion(int term1, int term2) {
			return term1 + " * " + term2 + " = ";
		}
	
		@Override
		public String getIncorrectAlternative(int term1, int term2, int alternativeNumber) {
			if( alternativeNumber % 2 == 0 ) {
				return "" + (term1 * term2 + alternativeNumber);
			} else {
				return "" + (term1 * term2 - alternativeNumber);
			}
		}
	
		@Override
		public String getCorrectAnswer(int term1, int term2) {
			return "" + (term1 * term2);
		}
	
	}
	private static class DivideQuestionFactory implements NumericQuestionFactory {
		@Override
		public String getNewQuestion(int term1, int term2) {
			if( term2 == 0 ) {
				throw new IllegalArgumentException("Can't create divide question with divisor = 0");
			} else {
				return term1 + " / " + term2 + " = ";
			}
		}
	
		@Override
		public String getIncorrectAlternative(int term1, int term2, int alternativeNumber) {
			if( alternativeNumber % 2 == 0 ) {
				return "" + (term1 / term2 + alternativeNumber);
			} else {
				return "" + (term1 / term2 - alternativeNumber);
			}
		}
	
		@Override
		public String getCorrectAnswer(int term1, int term2) {
			return "" + (term1 / term2);
		}
	
	}
}
