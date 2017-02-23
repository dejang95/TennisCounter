package com.example.android.tennisapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int pointsPlayer1 = 0;
    int pointsPlayer2 = 0;

    int gamesPlayer1 = 0;
    int gamesPlayer2 = 0;

    int setsPlayer1 = 0;
    int setsPlayer2 = 0;

    boolean tieBreak = false;
    boolean player1WonTheMatch = false;
    boolean player2WonTheMatch = false;

    Chronometer simpleChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
    }

    // Add points, games and sets to Player 1

    public void addPointPlayer1(View v) {

        if (gamesPlayer1 == 6 && gamesPlayer2 == 6) tieBreak = true;

        if (tieBreak == false && player1WonTheMatch == false && player2WonTheMatch == false) {

            if (pointsPlayer1 == 3 && pointsPlayer2 < pointsPlayer1) {
                pointsPlayer1 = pointsPlayer2 = 0;
                displayPointsPlayer1(pointsPlayer1);
                displayPointsPlayer2(pointsPlayer2);
                addGamesPlayer1();
            } else if (pointsPlayer1 == 4 && pointsPlayer2 == 3) {
                pointsPlayer1 = pointsPlayer2 = 0;
                displayPointsPlayer1(pointsPlayer1);
                displayPointsPlayer2(pointsPlayer2);
                addGamesPlayer1();
            } else if (pointsPlayer1 == 3 && pointsPlayer2 == 4) {
                pointsPlayer1 = pointsPlayer2 = 3;
                displayPointsPlayer1(40);
                displayPointsPlayer2(40);
            } else if (pointsPlayer1 <= 3) {
                ++pointsPlayer1;
                if (pointsPlayer1 == 0) displayPointsPlayer1(pointsPlayer1);
                else if (pointsPlayer1 == 1) displayPointsPlayer1(15);
                else if (pointsPlayer1 == 2) displayPointsPlayer1(30);
                else if (pointsPlayer1 == 3) displayPointsPlayer1(40);
                else if (pointsPlayer1 == 4) displayAdvantagePlayer1("AD");
            }
        } else if (player1WonTheMatch == false && player2WonTheMatch == false && tieBreak == true) {
            tieBreakMethod("Player1");
        }

        detectImportantInfo();
    }

    public void addGamesPlayer1() {

        if (gamesPlayer1 == 6 && gamesPlayer2 == 5) {
            gamesPlayer1 = gamesPlayer2 = 0;
            setsPlayer1++;
            displayGamesPlayer1(gamesPlayer1);
            displayGamesPlayer2(gamesPlayer2);
            displaySetsPlayer1(setsPlayer1);
        } else if (gamesPlayer1 == 5 && gamesPlayer2 < 5) {
            gamesPlayer1 = gamesPlayer2 = 0;
            setsPlayer1++;
            displayGamesPlayer1(gamesPlayer1);
            displayGamesPlayer2(gamesPlayer2);
            displaySetsPlayer1(setsPlayer1);
        } else if (gamesPlayer1 == 5 && gamesPlayer2 == 5) {
            gamesPlayer1++;
            displayGamesPlayer1(gamesPlayer1);
        } else if (gamesPlayer1 <= 5) {
            gamesPlayer1++;
            displayGamesPlayer1(gamesPlayer1);
        } else if (gamesPlayer1 == 6 && gamesPlayer2 == 6) {
            tieBreak = true;
        }
        checkIfWonTheMatch();
    }

    // Add points, games and sets to Player 2

    public void addPointPlayer2(View v) {

        if (gamesPlayer1 == 6 && gamesPlayer2 == 6) tieBreak = true;

        if (tieBreak == false && player1WonTheMatch == false && player2WonTheMatch == false) {

            if (pointsPlayer2 == 3 && pointsPlayer1 < pointsPlayer2) {
                pointsPlayer2 = pointsPlayer1 = 0;
                displayPointsPlayer2(pointsPlayer2);
                displayPointsPlayer1(pointsPlayer1);
                addGamesPlayer2();
            } else if (pointsPlayer2 == 4 && pointsPlayer1 == 3) {
                pointsPlayer2 = pointsPlayer1 = 0;
                displayPointsPlayer2(pointsPlayer2);
                displayPointsPlayer1(pointsPlayer1);
                addGamesPlayer2();
            } else if (pointsPlayer2 == 3 && pointsPlayer1 == 4) {
                pointsPlayer2 = pointsPlayer1 = 3;
                displayPointsPlayer2(40);
                displayPointsPlayer1(40);
            } else if (pointsPlayer2 <= 3) {
                ++pointsPlayer2;
                if (pointsPlayer2 == 0) displayPointsPlayer2(pointsPlayer2);
                else if (pointsPlayer2 == 1) displayPointsPlayer2(15);
                else if (pointsPlayer2 == 2) displayPointsPlayer2(30);
                else if (pointsPlayer2 == 3) displayPointsPlayer2(40);
                else if (pointsPlayer2 == 4) displayAdvantagePlayer2("AD");
            }
        } else if (player1WonTheMatch == false && player2WonTheMatch == false && tieBreak == true) {
            detectImportantInfo();
            tieBreakMethod("Player2");
        }
        detectImportantInfo();
    }

    public void addGamesPlayer2() {

        if (gamesPlayer2 == 6 && gamesPlayer1 == 5) {
            gamesPlayer2 = gamesPlayer1 = 0;
            setsPlayer2++;
            displayGamesPlayer1(gamesPlayer1);
            displayGamesPlayer2(gamesPlayer2);
            displaySetsPlayer2(setsPlayer2);
        } else if (gamesPlayer2 == 5 && gamesPlayer1 < 5) {
            gamesPlayer2 = gamesPlayer1 = 0;
            setsPlayer2++;
            displayGamesPlayer1(gamesPlayer1);
            displayGamesPlayer2(gamesPlayer2);
            displaySetsPlayer2(setsPlayer2);
        } else if (gamesPlayer2 == 5 && gamesPlayer1 == 5) {
            gamesPlayer2++;
            displayGamesPlayer2(gamesPlayer2);
        } else if (gamesPlayer2 <= 5) {
            gamesPlayer2++;
            displayGamesPlayer2(gamesPlayer2);
        } else if (gamesPlayer2 == 6 && gamesPlayer1 == 6) {
            tieBreak = true;
        }
        checkIfWonTheMatch();
    }

    public void checkIfWonTheMatch() {

        if (setsPlayer1 > setsPlayer2 && setsPlayer1 == 3) player1WonTheMatch = true;
        else if (setsPlayer2 > setsPlayer1 && setsPlayer2 == 3) player2WonTheMatch = true;

        detectImportantInfo();
    }

    // In a case of a TIE Break, this method is activated.

    public void tieBreakMethod(String player) {

        int advantagePlayer1 = pointsPlayer1 - pointsPlayer2;
        int advantagePlayer2 = pointsPlayer2 - pointsPlayer1;

        if (player == "Player1" && pointsPlayer1 < 6) {
            ++pointsPlayer1;
            displayPointsPlayer1(pointsPlayer1);
        } else if (player == "Player2" && pointsPlayer2 < 6) {
            ++pointsPlayer2;
            displayPointsPlayer2(pointsPlayer2);
        } else if (player == "Player1" && pointsPlayer1 >= 6 && advantagePlayer1 >= 1) {
            pointsPlayer1 = 0;
            pointsPlayer2 = 0;
            gamesPlayer1 = 0;
            gamesPlayer2 = 0;
            ++setsPlayer1;
            tieBreak = false;
            displayPointsPlayer1(pointsPlayer1);
            displayPointsPlayer2(pointsPlayer2);
            displayGamesPlayer1(gamesPlayer1);
            displayGamesPlayer2(gamesPlayer2);
            displaySetsPlayer1(setsPlayer1);
        } else if (player == "Player2" && pointsPlayer2 >= 6 && advantagePlayer2 >= 1) {
            pointsPlayer2 = 0;
            pointsPlayer1 = 0;
            gamesPlayer2 = 0;
            gamesPlayer1 = 0;
            ++setsPlayer2;
            tieBreak = false;
            displayPointsPlayer2(pointsPlayer2);
            displayPointsPlayer1(pointsPlayer1);
            displayGamesPlayer2(gamesPlayer2);
            displayGamesPlayer1(gamesPlayer1);
            displaySetsPlayer2(setsPlayer2);
        } else if (player == "Player1" && pointsPlayer1 >= 6 && advantagePlayer1 <= 0) {
            ++pointsPlayer1;
            displayPointsPlayer1(pointsPlayer1);
        } else if (player == "Player2" && pointsPlayer2 >= 6 && advantagePlayer2 <= 0) {
            ++pointsPlayer2;
            displayPointsPlayer2(pointsPlayer2);
        }
    }

    // ***** Start a new game (RESET SCORE - STARTS TIMER) *****

    public void startNewGame(View v) {
        pointsPlayer1 = pointsPlayer2 = gamesPlayer1 = gamesPlayer2 = setsPlayer1 = setsPlayer2 =  0;
        player1WonTheMatch = player2WonTheMatch = false;
        displayPointsPlayer1(pointsPlayer1);
        displayPointsPlayer2(pointsPlayer2);
        displayGamesPlayer1(gamesPlayer1);
        displayGamesPlayer2(gamesPlayer2);
        displaySetsPlayer1(setsPlayer1);
        displaySetsPlayer2(setsPlayer2);
        displayImportantInfo("");
        simpleChronometer.start();
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        displayImportantInfo("the match has started!");
    }

    // Detects important Info

    public void detectImportantInfo() {

        String info = "";

        if (tieBreak == true) {
            info = "tie break!";
        } else if (player1WonTheMatch) {                                              //WORKS!
            info = "game. set. match - player 1";
            simpleChronometer.stop();
        } else if (player2WonTheMatch) {                                            // WORKS!
            info = "game. set. match - player 2";
            simpleChronometer.stop();
        } else if (pointsPlayer1 == pointsPlayer2 && pointsPlayer1 == 3) info = "deuce!";

        displayImportantInfo(info);
    }

    /**
     * Displays the number of Points in a single Game for Player 1.
     */
    public void displayPointsPlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.pointsPlayer1);
        scoreView.setText(String.valueOf(score));
    }

    // Display the Advantage for Player 1.
    public void displayAdvantagePlayer1(String ad) {
        TextView adView = (TextView) findViewById(R.id.pointsPlayer1);
        adView.setText(String.valueOf(ad));
    }

    /**
     * Displays the number of Points in a single Game for Player 2.
     */
    public void displayPointsPlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.pointsPlayer2);
        scoreView.setText(String.valueOf(score));
    }

    // Display the Advantage for Player 2.
    public void displayAdvantagePlayer2(String ad) {
        TextView adView = (TextView) findViewById(R.id.pointsPlayer2);
        adView.setText(String.valueOf(ad));
    }

    /**
     * Displays the number of Games won in a single Set by the Player 1.
     */
    public void displayGamesPlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.gamesPlayer1);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the number of Games won in a single Set by the Player 2.
     */
    public void displayGamesPlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.gamesPlayer2);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the number of SETS won in a MATCH by the Player 1.
     */
    public void displaySetsPlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.setsPlayer1);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the number of SETS won in a MATCH by the Player 1.
     */
    public void displaySetsPlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.setsPlayer2);
        scoreView.setText(String.valueOf(score));
    }

    // Displays Important notifications/info like, who won the match and so on...

    public void displayImportantInfo(String info) {
        TextView infoView = (TextView) findViewById(R.id.importantNotification);
        infoView.setText(String.valueOf(info));
    }

}
