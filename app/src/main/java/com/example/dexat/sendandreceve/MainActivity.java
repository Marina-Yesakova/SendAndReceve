package com.example.dexat.sendandreceve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String LOGTAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.example.dexat.sendandreceve.MESSAGE";
    public static final String EXTRA_LINE_COUNT = "com.example.dexat.sendandreceve.EXTRA_LINE_COUNT";
    public static final String EXTRA_CHAR_COUNT = "com.example.dexat.sendandreceve.EXTRA_CHAR_COUNT";
    public static final String EXTRA_WORD_COUNT = "com.example.dexat.sendandreceve.EXTRA_WORD_COUNT";
    private EditText editText;
    private static final String KEY_EDITTEXTSTRING = "editTextString";
    static final int EDIT_REQUEST = 1;   // the request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String strEditText = "";
        if (savedInstanceState != null) {
            strEditText = savedInstanceState.getString(KEY_EDITTEXTSTRING, "<default string>");
        }

        editText = (EditText) findViewById(R.id.editText);
        //editText.setText(strEditText);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // get the string data from the EditText widget
        String strEditText = editText.getText().toString();

        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(KEY_EDITTEXTSTRING, strEditText);
    }
    // onClick gets called when the button is clicked on. Log.d call in the onClick handler so that it outputs a string to logcat
    protected void onClick(View v) {
        Log.d(LOGTAG, "...onButtonClick...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOGTAG, "...onStart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOGTAG, "...onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOGTAG, "...onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOGTAG, "...onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOGTAG, "...onDestroy...");
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent intent) {
        // Check which request we're responding to
        if (requestCode == EDIT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The edit was successful, change the EditText widget's text
                String strResult = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                editText.setText(strResult);
                // The edit was successful, change the characterCount widget's text
                TextView textViewChar = (TextView) findViewById(R.id.characterCount);
                String strResultCharacterCount = intent.getStringExtra(MainActivity.EXTRA_CHAR_COUNT);
                textViewChar.setText(strResultCharacterCount);
                // The edit was successful, change the wordCount widget's text
                TextView textViewWord = (TextView) findViewById(R.id.wordCount);
                String strResultWordCount = intent.getStringExtra(MainActivity.EXTRA_WORD_COUNT);
                textViewWord.setText(strResultWordCount);
                // The edit was successful, change the lineCount widget's text
                TextView textViewLine = (TextView) findViewById(R.id.lineCount);
                String strResultLineCount = intent.getStringExtra(MainActivity.EXTRA_LINE_COUNT);
                textViewLine.setText(strResultLineCount);
            }
        }
    }
    // Called when the user taps the Send button
    public void onSendButtonClick(View view) {
        // When you want an activity to start a second activity, use an intent.
        // An intent isan "intent to do something". It's a message that you
        // send to Android, stating that you want another activity started.
        Intent intent = new Intent(this, ReceiveMessage.class);
        //extract the text from the EditText
        String message = editText.getText().toString();
        // You can add extra information to the intent with "extra's". The
        // putExtra method is overloaded so the value has a number of possible
        // types.
        intent.putExtra(EXTRA_MESSAGE, message);
        // pass the intent to android in the startActivity call
        startActivityForResult(intent, EDIT_REQUEST);
    }


}