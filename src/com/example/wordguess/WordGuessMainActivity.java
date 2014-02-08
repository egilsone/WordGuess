package com.example.wordguess;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wordguess.game.NumericGameFactory;
import com.example.wordguess.interfaces.Answer;
import com.example.wordguess.interfaces.Game;
import com.example.wordguess.interfaces.Question;

/**
 * Main activity
 * Handles "a lot" of display logic from the game
 * TODO:
 * 1. More dynamic ways of creating games (number of questions/alternatives)
 * 2. More dynamic ways of displaying the result
 * 3. More dynamic ways of handling quizzes (for example free text)
 */
public class WordGuessMainActivity extends Activity {
    private Game game = null;
    private int currentQuestionIndex = 0;
    // These final's should be set by the user on startup
    private final int numberOfQuestions = 5;
    private final int numberOfAlternatives = 3;
    private final boolean summary = true;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_word_guess_main);
        // main logic to initialize view
        loadCurrentQuestion();
    }

    /** User interaction - should be dynamic with (probably) an onClickListener **/
    public void button1Clicked(View view) {
    	buttonClicked(1, view);
    }
    public void button2Clicked(View view) {
    	buttonClicked(2, view);
    }
    public void button3Clicked(View view) {
    	buttonClicked(3, view);
    }

    /** Dummies to be able to handle the callbacks from the different layouts **/ 
    public void buttonNewGameClicked(View view) {
    	newGame(view);
    }
    public void buttonNewGameClickedFromResult(View view) {
    	newGame(view);
    }
    public void buttonNewGameClickedFromResultSummary(View view) {
    	newGame(view);
    }
    
    /** Initialize a new game and switch to the main view **/
    public void newGame(View view) {
    	game = NumericGameFactory.createSimpleGame(numberOfQuestions, numberOfAlternatives,null);
    	currentQuestionIndex = 0;
    	setContentView(R.layout.activity_word_guess_main);
    	loadCurrentQuestion();
    }
    /* 
     * Load everything for the current question - or if finished - move to result presentation
     * TODO: Cleanup!!! Handle the results in separate methods. 
     */
    private void loadCurrentQuestion() {
        // We could get here without a game at all - or with just a new initialized
    	boolean gameExists = game != null;
    	boolean finished = game!=null && game.isFinished();
    	int gameVisibility = gameExists ? View.VISIBLE : View.INVISIBLE;

    	//TODO: disable/enable/create as many buttons as there are in the current question
    	//      not only button1/button2/button3
        findViewById(R.id.question).setVisibility(gameVisibility);
        findViewById(R.id.questionNr).setVisibility(gameVisibility);
        findViewById(R.id.button1).setVisibility(gameVisibility);
        findViewById(R.id.button2).setVisibility(gameVisibility);
        findViewById(R.id.button3).setVisibility(gameVisibility);

        if( finished ) {
        	// TODO: more dynamic ways of displaying the result (should depend on the game - what type of quiz is it)
        	if( summary ) {
            	List<String> resultList = new ArrayList<String>();
            	List<Boolean> answerList = new ArrayList<Boolean>();
            	// Iterate the questions and answers - and show some indication of the answers
            	for( int i = 1; i <= game.getQuestions().size(); i++ ) {
            		Question q = game.getQuestions().get(i-1);
            		boolean correct = q.isAnsweredCorrectly();
            		StringBuffer buf = new StringBuffer(i + ": " + q.getQuestion() + q.getCorrectAnswer().getAnswer());
            		if( !correct ) {
                		buf.append(" - inte " + q.getUserAnswer().getAnswer());
            		}
            		resultList.add(buf.toString());
            		answerList.add(correct);
            	}

        		ArrayAdapter<String> strings = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, resultList);
            	setContentView(R.layout.result_summary);
            	ListView listView = (ListView)findViewById(R.id.resultSummaryList);
            	listView.setAdapter(strings);
            	int i = 0;
            	for( Boolean boolVal : answerList ) {
            		listView.setItemChecked(i, boolVal);
            		i++;
            	}
        	} else {
            	List<String> resultList = new ArrayList<String>();
            	// Iterate the questions and answers - and show some indication of the answers
            	// This is probably more useful when displaying some statistics of the supplied answers
            	for( int i = 1; i <= game.getQuestions().size(); i++ ) {
            		Question q = game.getQuestions().get(i-1);
            		resultList.add("Fråga " + i + ": " + q.getQuestion() );
            		for( Answer a : q.getPossibleAnswers() ) {
            			// Four cases for each answer
            			//   1. this answer was the users answer and it was correct => "RÄTT!"
            			//   2. this answer was the users answer but it was not correct => "DITT SVAR!" 
            			//   3. this answer was the correct answer but not the users answer => "RÄTT SVAR!"
            			//   4. none of the above - nor correct nor users answer
            			String extraText = ""; // case 4
            			boolean isThisTheUsersAnswer = q.getUserAnswer() == a;
            			if( isThisTheUsersAnswer ) {
            				// Case 1 and 2
            				extraText = a.isCorrect() ? "RÄTT!" : "DITT SVAR";
            			} else {
            				// Case 3 and 4 
            				extraText = a.isCorrect() ? "RÄTT SVAR" : "";
            			}
            			resultList.add( "  " + a.getAnswer() + " " + extraText);
            		}
            	}
        		
        		ArrayAdapter<String> strings = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultList);
            	setContentView(R.layout.result_layout);
            	ListView listView = (ListView)findViewById(R.id.resultsList);
            	listView.setAdapter(strings);
        	}
        } else if( game != null ) {
        	Question q = game.getQuestions().get(currentQuestionIndex);
        	TextView questionNrView = (TextView)findViewById(R.id.questionNr);
        	TextView questionView = (TextView)findViewById(R.id.question);
        	questionNrView.setText("Fråga nr " + (currentQuestionIndex+1) );
        	questionView.setText(q.getQuestion());
        	TextView alt1 = (TextView)findViewById(R.id.button1);
        	TextView alt2 = (TextView)findViewById(R.id.button2);
        	TextView alt3 = (TextView)findViewById(R.id.button3);
        	alt1.setText(q.getPossibleAnswers().get(0).getAnswer());
        	alt2.setText(q.getPossibleAnswers().get(1).getAnswer());
        	alt3.setText(q.getPossibleAnswers().get(2).getAnswer());
        }
	}

    /** Register the users answer in the game */
	private void buttonClicked(int alternative, View view) {
		// Register the answer
    	Question currentQuestion = game.getQuestions().get(currentQuestionIndex);
    	Answer currentAnswer     = currentQuestion.getPossibleAnswers().get(alternative-1);
    	currentQuestion.setUserAnswer(currentAnswer);
    	// Step forward to next question - or display the result
		currentQuestionIndex++;
		loadCurrentQuestion();
    }
}
