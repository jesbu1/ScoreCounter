package com.example.android.courtcounter;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This activity keeps track of the basketball score for 2 teams.
 */
public class MainActivity extends AppCompatActivity {
    //Saves states
    private static final String SCORE_TEAM_A_KEY = "score A";
    private static final String SCORE_TEAM_B_KEY = "score B";
    private static final String UNDO_A_KEY = "undo A";
    private static final String UNDO_B_KEY = "undo B";

    //Sound effect
    MediaPlayer swish;

    // Tracks the score for Team A
    int scoreTeamA = 0;

    // Tracks the score for Team B
    int scoreTeamB = 0;

    /**
     * Keeps hold of stuff for the undo button
     * int[] undoHolderA = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
     * int[] undoHolderB = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
     */

    // The vector for holding previous scores
    ArrayList<Integer> undoValuesA = new ArrayList<Integer>();
    ArrayList<Integer> undoValuesB = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        undoValuesA.add(scoreTeamA);
        undoValuesB.add(scoreTeamB);
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt(SCORE_TEAM_A_KEY);
            scoreTeamB = savedInstanceState.getInt(SCORE_TEAM_B_KEY);
            undoValuesA = savedInstanceState.getIntegerArrayList(UNDO_A_KEY);
            undoValuesB = savedInstanceState.getIntegerArrayList(UNDO_B_KEY);
        }
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);

        swish = MediaPlayer.create(this, R.raw.swish);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimpSlifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
        undoValuesA.add(scoreTeamA);
        swish.start();
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
        undoValuesA.add(scoreTeamA);
        swish.start();
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        undoValuesA.add(scoreTeamA);
        swish.start();
    }

    //The Undo A button
    public void undoA(View v) {
        if (undoValuesA.size() != 1) {
            undoValuesA.remove(undoValuesA.size() - 1);
            scoreTeamA = undoValuesA.get(undoValuesA.size() - 1);
            displayForTeamA(scoreTeamA);
        } else {
            undoValuesA.remove(undoValuesA.size() - 1);
            undoValuesA.add(0);
            scoreTeamA = undoValuesA.get(undoValuesA.size() - 1);
            displayForTeamA(scoreTeamA);

        }
    }

    /**
     * Increase the score for Team B by 1 point.
     */
    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
        undoValuesB.add(scoreTeamB);
        swish.start();
    }

    /**
     * Increase the score for Team B by 2 points.
     */
    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
        undoValuesB.add(scoreTeamB);
        swish.start();
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
        undoValuesB.add(scoreTeamB);
        swish.start();
    }

    //The Undo B button
    public void undoB(View v) {
        if (undoValuesB.size() != 1) {
            undoValuesB.remove(undoValuesB.size() - 1);
            scoreTeamB = undoValuesB.get(undoValuesB.size() - 1);
            displayForTeamB(scoreTeamB);
        } else {
            undoValuesB.remove(undoValuesB.size() - 1);
            undoValuesB.add(0);
            scoreTeamB = undoValuesB.get(undoValuesB.size() - 1);
            displayForTeamB(scoreTeamB);
        }
    }

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        undoValuesA.clear();
        undoValuesB.clear();
        undoValuesA.add(0);
        undoValuesB.add(0);

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //Save state information
        savedInstanceState.putInt(SCORE_TEAM_A_KEY, scoreTeamA);
        savedInstanceState.putInt(SCORE_TEAM_B_KEY, scoreTeamB);
        savedInstanceState.putIntegerArrayList(UNDO_A_KEY, undoValuesA);
        savedInstanceState.putIntegerArrayList(UNDO_B_KEY, undoValuesB);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
