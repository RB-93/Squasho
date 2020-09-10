package com.rohit.examples.android.squasho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class GameScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        // Fetching ids of all XML elements into View type variables.
        // Variable declaration for View/ Widgets
        TextView scoreViewA = findViewById(R.id.country_a_score);
        TextView scoreViewB = findViewById(R.id.country_b_score);
        TextView countryViewA = findViewById(R.id.country_a_text);
        TextView countryViewB = findViewById(R.id.country_b_text);
        TextView textViewHome = findViewById(R.id.home_text);
        TextView winner = findViewById(R.id.winner_text);

        String mCountryA;           // For saving winning country name
        String mCountryB;           // For saving winning country name

        int mScoreA;                // For saving winning scores
        int mScoreB;                // For saving winning scores

        // Getting few relevant views data received from previous activity
        Bundle scoreBundle = getIntent().getExtras();

        /*
            Displaying views values in current activity
            Checking if the bundle object is not empty (null) and
            then storing the data into View type Variables
        */
        if (scoreBundle != null) {
            mScoreA = scoreBundle.getInt("TEAM_A_SCORE");
            mScoreB = scoreBundle.getInt("TEAM_B_SCORE");
            mCountryA = scoreBundle.getString("TEAM_A_COUNTRY");
            mCountryB = scoreBundle.getString("TEAM_B_COUNTRY");

            //Setting view content with the data taken from Bundle object
            scoreViewA.setText(String.valueOf(mScoreA));
            scoreViewB.setText(String.valueOf(mScoreB));
            countryViewA.setText(mCountryA);
            countryViewB.setText(mCountryB);
            winner.setAllCaps(true);

            if (mScoreA > mScoreB) {
                winner.setText(getString(R.string.winner_text, mCountryA, "WON"));      // For saving winning country name
            } else if (mScoreB > mScoreA) {
                winner.setText(getString(R.string.winner_text, mCountryB, "WON"));      // For saving winning country name
            } else {
                winner.setText(getString(R.string.match_tied));             // For Equal score points, i.e.., Match tied!
            }
        }

        /*
            Hiding the 'gotoHome' from parent, then
            setting animation tweaks for the TextView
        */
        textViewHome.setVisibility(View.INVISIBLE);
        Animation slidein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);
        textViewHome.startAnimation(slidein);
        textViewHome.setVisibility(View.VISIBLE);

        /*
            Calling TextView onClick Interface for 'go to Home' view
            method call for re-initiating an activity from current activity
        */
        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(GameScoreActivity.this, ChooserActivity.class);
                startActivity(homeIntent);

                finish();           // Finished the current activity
            }
        });
    }

    // Requesting user to go back using 'Goto home' Button, feedback through Toast Notification.
    @Override
    public void onBackPressed() {
        Toast backToast = Toast.makeText(this, "Tap \'Goto home\' button to go back", Toast.LENGTH_SHORT);
        backToast.setGravity(Gravity.TOP, 0, 0);
        backToast.setMargin(0.0f, 0.1f);
        backToast.show();
    }
}