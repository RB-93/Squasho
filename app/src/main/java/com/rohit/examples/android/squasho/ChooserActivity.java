package com.rohit.examples.android.squasho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ChooserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Variable inclusion for making use of Spinners for TeamA and TeamB
    private Spinner teamA_spinner;
    private Spinner teamB_spinner;

    // Variable addition to make use of Play button
    private Button btn_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        // Fetching ids of all XML elements into View type variables.

        teamA_spinner = findViewById(R.id.spinner);
        teamB_spinner = findViewById(R.id.spinner2);
        btn_play = findViewById(R.id.play_button);

        /*
            Defining 'Locales' object for fetching Locales details.
            Locales is a Java Framework class which has the collection of all Countries, Codes, 3-letter Country Notation etc.
        */
        Locale[] locales = Locale.getAvailableLocales();

        // ArrayList object to store character typed countries/
        ArrayList<String> countryList = new ArrayList<>();

        // #params country to store All counties names
        String country;
        for (Locale locale : locales) {

            country = locale.getDisplayCountry();
            if (country.trim().length() > 2 && !countryList.contains(country))
                countryList.add(country);   // Adding the countries into ArrayList type object.

        }
        Collections.sort(countryList);      // Method call to sort all Countries alphabetically

        /*
               ArrayAdapter typed object,
               #params dataAdapter, dataAdapter2 to add Countries list into Spinner of each TeamView
        */
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, countryList);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, countryList);

        // Setting dropdown layout for Spinner menus
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_item);

        //Setting each country spinner from ArrayAdapter type variable.;
        teamA_spinner.setAdapter(dataAdapter);
        teamB_spinner.setAdapter(dataAdapter2);

        // Setting Play Button visibility to ZERO until Team are different.
        btn_play.setVisibility(View.INVISIBLE);

        // Setting clickListener interfaces for both spinners.
        teamA_spinner.setOnItemSelectedListener(this);
        teamB_spinner.setOnItemSelectedListener(this);
    }

    // Spinner onItemSelected definition to handle if Country is selected.
    @Override
    public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) {
        final String item1 = teamA_spinner.getSelectedItem().toString();
        final String item2 = teamB_spinner.getSelectedItem().toString();

        if (item1.equals(item2) && item2.equals(item1)) {
            btn_play.setAlpha(0.5f);
            btn_play.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Teams cannot be same", Toast.LENGTH_SHORT).show();
        } else {
            if (!btn_play.isEnabled()) {
                btn_play.setVisibility(View.VISIBLE);
                btn_play.setAlpha(1.0f);
                btn_play.setEnabled(true);
            }
            btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentGame = new Intent(ChooserActivity.this, GameActivity.class);
                    intentGame.putExtra("TEAM_A_COUNTRY", item1);
                    intentGame.putExtra("TEAM_B_COUNTRY", item2);
                    startActivity(intentGame);
                }
            });
        }
    }

    // Spinner onNothingSelected definition to handle (usually left as Empty)
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}