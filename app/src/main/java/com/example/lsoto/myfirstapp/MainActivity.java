package com.example.lsoto.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // create a new INtent
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        // get a reference of the edit text view
        EditText editText = (EditText) findViewById(R.id.edit_message);
        // get the text
        String message = editText.getText().toString();
        // add a key/value into the intent
        intent.putExtra(EXTRA_MESSAGE, message);
        // start the activity
        startActivity(intent);
    }
}
