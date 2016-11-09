package com.example.lsoto.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";

    int mCurrentScore = 100;
    int mCurrentLevel = 3;

    @Override
    // Because the onCreate() method is called whether the system is creating a new instance of your
    // activity or recreating a previous one, you must check whether the state Bundle is null
    // before you attempt to read it. If it is null, then the system is creating a new instance of
    // the activity, instead of restoring a previous one that was destroyed.
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


        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        } else {
            // Probably initialize members with default values for a new instance
        }
    }

    // The system calls onRestoreInstanceState() only if there is a saved state to restore,
    // so you do not need to check whether the Bundle is null:
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
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
    // When the system calls onPause() for your activity, it technically means your activity is still partially visible,
    // but most often is an indication that the user is leaving the activity and it will soon enter the Stopped state.
    // Generally, you should not use onPause() to store user changes to permanent storage.
    // However, you should avoid performing CPU-intensive work during onPause(), such as writing to a database,
    // because it can slow the visible transition to the next activity.
    // You should instead perform heavy-load shutdown operations during onStop().
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        // Release the Camera because we don't need it when paused
        // and other activities might need to use it.
        //if (mCamera != null) {
            //mCamera.release();
            //mCamera = null;
        //}
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Get the Camera instance as the activity achieves full user focus
        //if (mCamera == null) {
            //initializeCamera(); // Local method to handle camera init
        //}
    }

    @Override
    // When your activity receives a call to the onStop() method, it's no longer visible and
    // should release almost all resources that aren't needed while the user is not using it.
    // You should use onStop() to perform larger, more CPU intensive shut-down operations, such as writing information to a database.
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        // Save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        /*
        ContentValues values = new ContentValues();
        values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
        values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());

        getContentResolver().update(
                mUri,    // The URI for the note to update.
                values,  // The map of column names and new values to apply to them.
                null,    // No SELECT criteria are used.
                null     // No WHERE columns are used.
        );
        */
    }

    @Override
    // You should usually use the onStart() callback method as the counterpart to the onStop() method,
    // because the system calls onStart() both when it creates your activity and when it restarts
    // the activity from the stopped state.
    protected void onStart() {
        super.onStart();  // Always call the superclass method first

        // The activity is either being restarted or started for the first time
        // so this is where we should make sure that GPS is enabled
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            // Create a dialog here that requests the user to enable GPS, and use an intent
            // with the android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS action
            // to take the user to the Settings screen to enable GPS when they click "OK"
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }

    @Override
    // As your activity begins to stop, the system calls onSaveInstanceState() so your activity
    // can save state information with a collection of key-value pairs.
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state

        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}
