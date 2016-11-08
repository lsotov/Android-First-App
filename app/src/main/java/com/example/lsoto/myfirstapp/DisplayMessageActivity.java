package com.example.lsoto.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // get the Intent that started the Activity
        Intent intent = getIntent();
        // get the data by key
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // create a text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        // get a reference to the layout
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        // add the view to the layout
        layout.addView(textView);
    }
}
