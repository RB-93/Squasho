package com.rohit.examples.android.squasho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetching ids of all XML elements into View type variables.
        // Buttons variables for 'Rules' and 'Choose Team' Button
        Button btn_rule = findViewById(R.id.rule_button);
        Button btn_choose = findViewById(R.id.choose_button);

        // Button onClick Interface definition for triggering desired Intent to next activity
        btn_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ruleIntent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(ruleIntent);
            }
        });

        // Button onClick Interface definition for triggering desired Intent to next activity
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseIntent = new Intent(MainActivity.this, ChooserActivity.class);
                startActivity(chooseIntent);

            }
        });
    }
}