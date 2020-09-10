package com.rohit.examples.android.squasho;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // Fetching ids of all XML elements into View type variables.
        // Variable for displaying Rules of the Sports
        TextView tv_rules = findViewById(R.id.rules_text);

        // Checking Build Version of Device to be Android O for "JUSTIFICATION_MODE_INTER_WORD" formatting attribute
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_rules.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        // Setting textview content from String resource
        tv_rules.setText(R.string.rules, TextView.BufferType.NORMAL);
    }
}