package com.example.lsoto.myfirstapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the user interface layout for this Activity
        // The layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.activity_main);

        android.os.Debug.startMethodTracing();

        // Get a string resource from your app's Resources
        String hello = getResources().getString(R.string.hello_world);
        // Write a Log
        Log.d(MainActivity.class.getSimpleName(), hello);
        // Checking system version at runtime
        // e.g. Make sure we're running on Honeycomb or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            Log.d("Checking Version", "At least HoneyComb");
        }
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // create a new Intent
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

    @Override
    //Most apps don't need to implement this method because local class references are destroyed with the activity
    // and your activity should perform most cleanup during onPause() and onStop().
    // However, if your activity includes background threads that you created during onCreate()
    // or other long-running resources that could potentially leak memory if not properly closed,
    // you should kill them during onDestroy().
    public void onDestroy(){
        super.onDestroy();  // Always call the superclass
        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        // Release the Camera because we don't need it when paused
        // and other activities might need to use it.
        //if (mCamera != null) {
            //mCamera.release();
            //mCamera = null;
        //}
    }
}
