package com.rohit.examples.android.squasho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView teamScoreA;
    private TextView teamScoreB;

    /*
        params teamACountry, teamCountry for displaying their names
    */
    private String teamACountry;
    private String teamBCountry;

    /*
        params scoreA, scoreB for saving scores for Team A viz., Country A & Team B viz., Country B
        params countFloorA, countFloorB for counting Floor button clicks
        params countServeA, countServeB for counting Serve button clicks
    */

    private int scoreA = 0;
    private int scoreB = 0;

    private int countFloorA = 0;
    private int countFloorB = 0;

    private int countServeA = 0;
    private int countServeB = 0;

    // hit Button for Team A & Team B
    private Button hitA;
    private Button hitB;

    // floor Button for Team A & Team B
    private Button floorA;
    private Button floorB;

    // serve Button for Team A & Team B
    private Button serveA;
    private Button serveB;

    // stroke Button for Team A & Team B
    private Button strokeA;
    private Button strokeB;

    // out Button for Team A & Team B
    private Button outA;
    private Button outB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Fetching ids of all XML elements into View type variables.

        /*
        Declaring variables for all view types in XML
    */
        TextView teamA = findViewById(R.id.team_a_text);
        TextView teamB = findViewById(R.id.team_b_text);

        teamScoreA = findViewById(R.id.team_a_score);
        teamScoreB = findViewById(R.id.team_b_score);

        hitA = findViewById(R.id.team_a_hit);
        hitB = findViewById(R.id.team_b_hit);

        serveA = findViewById(R.id.team_a_serve);
        serveB = findViewById(R.id.team_b_serve);

        floorA = findViewById(R.id.team_a_floor);
        floorB = findViewById(R.id.team_b_floor);

        strokeA = findViewById(R.id.team_a_stroke);
        strokeB = findViewById(R.id.team_b_stroke);

        outA = findViewById(R.id.team_a_out);
        outB = findViewById(R.id.team_b_out);

        // Bundle package object to receiving data from activities using Intents.
        Bundle extras = getIntent().getExtras();

        /*  Checking if Bundles object is NULL, and
            storing those object values to the variables
        */
        if (extras != null) {

            teamACountry = extras.getString("TEAM_A_COUNTRY");
            teamBCountry = extras.getString("TEAM_B_COUNTRY");

            teamA.setText(String.valueOf(teamACountry));
            teamB.setText(String.valueOf(teamBCountry));
        }
        disableButtons();           // Call to set Buttons as 'disabled' as soon as the Activity is triggered.
    }

    //Method definition for displaying Country A scores.
    private void displayForTeamA(int score) {
        teamScoreA.setText(String.valueOf(score));
    }

    //Method definition for displaying Country B scores.
    private void displayForTeamB(int score) {
        teamScoreB.setText(String.valueOf(score));
    }

    //Method definition for resetting scores to ZERO.
    public void resetScore(View view) {
        scoreA = 0;
        scoreB = 0;
        countServeA = 0;
        countServeB = 0;

        /*
            Method call to display scores of for Country A and Country B
            Method call 'disableButtons' to disable the buttons
        */
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        disableButtons();

        /*
            Check to serveA & serveB buttons isn't enabled
            if YES, the Setting both as ENABLED
        */
        if (!serveA.isEnabled() || !serveB.isEnabled()) {
            serveA.setEnabled(true);
            serveB.setEnabled(true);
        }

        // Toast to display/feedback user about the Score reset information
        Toast resetToast = Toast.makeText(this, "All scores are reset", Toast.LENGTH_SHORT);
        resetToast.setGravity(Gravity.CENTER, 0, 0);
        resetToast.show();
    }

    // Method definition to set buttons disabled
    private void disableButtons() {
        hitA.setEnabled(false);
        floorA.setEnabled(false);
        strokeA.setEnabled(false);
        outA.setEnabled(false);

        hitB.setEnabled(false);
        floorB.setEnabled(false);
        strokeB.setEnabled(false);
        outB.setEnabled(false);
    }

    /*
        Method definition to trigger Final Activity i.e., ScoreCard -- 'GameScoreActivity'
        Sending the data to next activity -- Final scores of both the Countries (teams).
    */
    private void scorecardIntent() {
        Intent scoreIntent = new Intent(GameActivity.this, GameScoreActivity.class);
        scoreIntent.putExtra("TEAM_A_SCORE", scoreA);
        scoreIntent.putExtra("TEAM_B_SCORE", scoreB);
        scoreIntent.putExtra("TEAM_A_COUNTRY", teamACountry);
        scoreIntent.putExtra("TEAM_B_COUNTRY", teamBCountry);
        startActivity(scoreIntent);

        finish();
    }

    // Method definition for SERVE button clicks for TEAM A
    public void serveForTeamA(View view) {

        if (countServeA == 0) {
            serveA.setEnabled(false);

            serveB.setEnabled(false);
            hitB.setEnabled(true);
            floorB.setEnabled(true);
            strokeB.setEnabled(true);
            outB.setEnabled(true);
            countServeA++;

        }
    }

    // Method definition for HIT button clicks for TEAM A
    public void hitForTeamA(View view) {
        if (scoreA < 12) {
            if (!hitB.isEnabled() && !floorB.isEnabled() && !strokeB.isEnabled() && !outB.isEnabled()) {
                hitB.setEnabled(true);
                floorB.setEnabled(true);
                strokeB.setEnabled(true);
                outB.setEnabled(true);
            }

            scoreA = scoreA + 1;
            displayForTeamA(scoreA);
            if (scoreA == 11) {
                disableButtons();
                scorecardIntent();
            }
        }
    }

    // Method definition for HIT button clicks for TEAM A
    public void floorForTeamA(View view) {
        if (scoreB < 12) {
            if (countFloorA < 3) {
                if (!hitB.isEnabled() && !floorB.isEnabled() && !strokeB.isEnabled() && !outB.isEnabled()) {
                    hitB.setEnabled(true);
                    floorB.setEnabled(true);
                    strokeB.setEnabled(true);
                    outB.setEnabled(true);
                    countFloorA = countFloorA + 1;
                } else {
                    countFloorA = countFloorA + 1;

                    if (countFloorA == 2) {
                        if (scoreA == 0) {
                            teamScoreA.setText("0");
                        } else {
                            scoreA = scoreA - 1;
                            displayForTeamA(scoreA);
                        }
                        scoreB = scoreB + 1;
                        if (scoreB == 11) {
                            disableButtons();
                            scorecardIntent();
                        }
                        floorA.setEnabled(false);
                        countFloorA = 0;
                        Toast toast = Toast.makeText(this, "Wait for your Serve", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.START, 0, 0);
                        toast.show();
                    }
                }
            }
        }
    }

    // Method definition for STROKE button clicks for TEAM A
    public void strokeForTeamA(View view) {
        if (scoreB < 12) {
            if (!hitB.isEnabled() && !floorB.isEnabled() && !strokeB.isEnabled() && !outB.isEnabled()) {
                hitB.setEnabled(true);
                floorB.setEnabled(true);
                strokeB.setEnabled(true);
                outB.setEnabled(true);
            } else {
                if (scoreA == 0) {
                    teamScoreA.setText("0");
                } else {
                    scoreA = scoreA - 1;
                    displayForTeamA(scoreA);
                }
                scoreB = scoreB + 1;
                displayForTeamB(scoreB);
                if (scoreB == 11) {
                    disableButtons();
                    scorecardIntent();
                }
            }
        }
    }

    // Method definition for OUT button clicks for TEAM A
    public void outForTeamA(View view) {
        if (scoreB < 12) {
            if (!hitB.isEnabled() && !floorB.isEnabled() && !strokeB.isEnabled() && !outB.isEnabled()) {
                hitB.setEnabled(true);
                floorB.setEnabled(true);
                strokeB.setEnabled(true);
                outB.setEnabled(true);

                scoreB = scoreB + 1;
                displayForTeamB(scoreB);
            } else {
                if (scoreA == 0) {
                    teamScoreA.setText("0");
                } else {
                    scoreA = scoreA - 1;
                    displayForTeamA(scoreA);
                }
                scoreB = scoreB + 1;
                displayForTeamB(scoreB);

                if (scoreB == 11) {
                    disableButtons();
                    scorecardIntent();
                }
            }

            if (!serveB.isEnabled()) {
                serveB.setEnabled(true);

            }
            floorA.setEnabled(true);
            countServeA = countServeB = 0;
        }
    }

    // Method definition for SERVE button clicks for TEAM B
    public void serveForTeamB(View view) {
        if (countServeB == 0) {
            serveB.setEnabled(false);

            serveA.setEnabled(false);
            hitA.setEnabled(true);
            floorA.setEnabled(true);
            strokeA.setEnabled(true);
            outA.setEnabled(true);
            countServeB++;
        }
    }

    // Method definition for HIT button clicks for TEAM B
    public void hitForTeamB(View view) {
        if (scoreB < 12) {
            if (!hitA.isEnabled() && !floorA.isEnabled() && !strokeA.isEnabled() && !outA.isEnabled()) {
                hitA.setEnabled(true);
                floorA.setEnabled(true);
                strokeA.setEnabled(true);
                outA.setEnabled(true);
            }
            scoreB = scoreB + 1;
            displayForTeamB(scoreB);

            if (scoreB == 11) {
                disableButtons();
                scorecardIntent();
            }
        }
    }

    // Method definition for FLOOR button clicks for TEAM B
    public void floorForTeamB(View view) {
        if (scoreA < 12) {
            if (countFloorB < 3) {
                if (!hitA.isEnabled() && !floorA.isEnabled() && !strokeA.isEnabled() && !outA.isEnabled()) {
                    hitA.setEnabled(true);
                    floorA.setEnabled(true);
                    strokeA.setEnabled(true);
                    outA.setEnabled(true);
                    countFloorB = countServeB + 1;
                } else {
                    countFloorB = countFloorB + 1;

                    if (countFloorB == 2) {
                        if (scoreB == 0) {
                            teamScoreB.setText("0");
                        } else {
                            scoreB = scoreB - 1;
                            displayForTeamB(scoreB);
                        }
                        scoreA = scoreA + 1;
                        displayForTeamA(scoreA);
                        if (scoreA == 11) {
                            disableButtons();
                            scorecardIntent();
                        }
                        floorB.setEnabled(false);
                        countFloorB = 0;
                        Toast toast = Toast.makeText(this, "Wait for your Serve", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.END, 0, 0);
                        toast.show();
                    }
                }
            }
        }
    }

    // Method definition for STROKE button clicks for TEAM B
    public void strokeForTeamB(View view) {
        if (scoreA < 12) {
            if (!hitA.isEnabled() && !floorA.isEnabled() && !strokeA.isEnabled() && !outA.isEnabled()) {
                hitA.setEnabled(true);
                floorA.setEnabled(true);
                strokeA.setEnabled(true);
                outA.setEnabled(true);
            } else {
                if (scoreB == 0) {
                    teamScoreB.setText("0");
                } else {
                    scoreB = scoreB - 1;
                    displayForTeamB(scoreB);
                }
                scoreA = scoreA + 1;
                displayForTeamA(scoreA);
                if (scoreA == 11) {
                    disableButtons();
                    scorecardIntent();
                }
            }
        }
    }

    // Method definition for OUT botton clicks for TEAM B
    public void outForTeamB(View view) {
        if (scoreA < 12) {
            if (!hitA.isEnabled() && !floorA.isEnabled() && !strokeA.isEnabled() && !outA.isEnabled()) {
                hitA.setEnabled(true);
                floorA.setEnabled(true);
                strokeA.setEnabled(true);
                outA.setEnabled(true);

                scoreA = scoreA + 1;
                displayForTeamA(scoreA);
            } else {
                if (scoreB == 0) {
                    teamScoreB.setText("0");
                } else {
                    scoreB = scoreB - 1;
                    displayForTeamB(scoreB);
                }

                scoreA = scoreA + 1;
                displayForTeamA(scoreA);

                if (scoreA == 11) {
                    disableButtons();
                    scorecardIntent();
                }
            }

            if (!serveA.isEnabled()) {
                serveA.setEnabled(true);
            }
            floorB.setEnabled(true);
            countServeB = countServeA = 0;
        }
    }

    /*
        Method definition to check if,
        Back Button is pressed.
        If YES, the data received is destroyed before the activity ends.
    */
    @Override
    public void onBackPressed() {
        getIntent().removeExtra("TEAM_A_COUNTRY");
        getIntent().removeExtra("TEAM_B_COUNTRY");

        super.onBackPressed();
    }

    // Method definition to trigger AlertDialog if BACK BUTTON pressed, to ask user if they really wants to Quit
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // AlertDialog for back button navigation
            AlertDialog.Builder backDialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog);
            backDialog
                    .setTitle("BACK TO HOME?")
                    .setMessage("Are you want to go back? \nThis action will reset your progress.")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}