package com.example.wordguess;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wordguess.game.GameFactory;
import com.example.wordguess.interfaces.Answer;
import com.example.wordguess.interfaces.Game;
import com.example.wordguess.interfaces.Question;
import com.example.wordguess.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class WordGuessMainActivity extends Activity {
    private Game game = null;
    private int gameState = 0;
    private int numberOfQuestions = 5;
    private int numberOfAlternatives = 3;
    // private List<String> results = new ArrayList<String>();
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_word_guess_main);
        // Main logic
        loadCurrentQuestion();
    }

    /** User interaction **/
    public void button1Clicked(View view) {
    	buttonClicked(1, view);
    }
    public void button2Clicked(View view) {
    	buttonClicked(2, view);
    }
    public void button3Clicked(View view) {
    	buttonClicked(3, view);
    }
    public void buttonNewGameClicked(View view) {
    	newGame(view);
    }
    public void buttonNewGameClickedFromResult(View view) {
    	newGame(view);
    }
    /** Some sort of maintaining state **/
    public void newGame(View view) {
    	game = GameFactory.createSimpleGame(numberOfQuestions, numberOfAlternatives);
    	gameState = 0;
    	setContentView(R.layout.activity_word_guess_main);
    	loadCurrentQuestion();
    }
    /** Load everything from question number i **/
    private void loadCurrentQuestion() {
        // Inaktivera svarsknapparna vid uppstart
    	boolean gameExists = game != null;
    	boolean finished = game!=null && game.isFinished();
    	int buttonVisibility = gameExists ? View.VISIBLE : View.INVISIBLE;

    	/*
        findViewById(R.id.button1).setEnabled(!finished);
        findViewById(R.id.button2).setEnabled(!finished);
        findViewById(R.id.button3).setEnabled(!finished);
        */
        findViewById(R.id.button1).setVisibility(buttonVisibility);
        findViewById(R.id.button2).setVisibility(buttonVisibility);
        findViewById(R.id.button3).setVisibility(buttonVisibility);

        if( finished ) {
        	List<String> resultList = new ArrayList<String>();
        	// Iterate the questions and answers - and show some indication of the answers
        	for( int i = 1; i <= game.getQuestions().size(); i++ ) {
        		Question q = game.getQuestions().get(i-1);
        		resultList.add("Fråga " + i + ": " + q.getQuestion() );
        		for( Answer a : q.getPossibleAnswers() ) {
        			String myText = a.getAnswer();
        			String extraText = "";
        			// Var det detta användaren svarade
        			boolean isUserAnswer = q.getUserAnswer() == a;
        			if( isUserAnswer ) {
        				// Om det är det svar man lämnat skall vi ange rätt/fel
        				extraText = a.isCorrect() ? "RÄTT!" : "DITT SVAR";
        			} else {
        				// annars skall vi skriva ut 
        				extraText = a.isCorrect() ? "RÄTT SVAR" : "";
        			}
        			// Tre fall:
        			// 1. användarens svar är rätt => "RÄTT!"
        			// 2. användarens svar är fel  => Skriv ut "RÄTT SVAR!" på den korrekta, DITT SVAR på användarens
        			// 3. varken eller - skriv ingenting
        			resultList.add( "  " + myText + " " + extraText);
        		}
        	}
            // Initiera an adapter redan nu
        	// results.add("Empty string");
        	// Beware - har använder vi den inbyggda funktionaliteten (layouten)
        	ArrayAdapter<String> strings = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultList);
        	setContentView(R.layout.result_layout);
        	ListView listView = (ListView)findViewById(R.id.resultsView);
        	listView.setAdapter(strings);
        } else if( game!= null ) {
        	Question q = game.getQuestions().get(gameState);
        	TextView questionNrView = (TextView)findViewById(R.id.questionNr);
        	TextView questionView = (TextView)findViewById(R.id.question);
        	questionNrView.setText("Fråga nr " + (gameState+1) );
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
    	Question currentQuestion = game.getQuestions().get(gameState);
    	Answer currentAnswer     = currentQuestion.getPossibleAnswers().get(alternative-1);
    	currentQuestion.setUserAnswer(currentAnswer);
    	// Step forward to next question - or display the result
		gameState++;
		loadCurrentQuestion();
    }
}
