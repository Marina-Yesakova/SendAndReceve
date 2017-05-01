package com.example.dexat.sendandreceve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ReceiveMessage extends AppCompatActivity {
    private final static String LOGTAG = "ReceiveMessage";
    public static final String EDIT_RESULT = "EditResult";
    private static final String KEY_EDITTEXTSTRING = "editTextString";
    private TextView textView;

    //start ReceiveMessage activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //calling super constructor
        super.onCreate(savedInstanceState);
        //shows the activity_receive_message layout
        setContentView(R.layout.activity_receive_message);
        //getting intent form MainActivity
        Intent intent = getIntent();
        //extract the text that was placed as an Extra in the Intent
        String messageText = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // Capture the layout's activity_main and set the string as its text
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(messageText);
    }

    protected void onClickOk(View view) {
        String message = textView.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(EDIT_RESULT, message);
        setResult(RESULT_OK, intent);

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // get the string data from the EditText widget
        String strEditText = textView.getText().toString();

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



    // Called when the user taps the Ok button
    public void onClickOkay(View view) {
        //creating intent
        Intent intent = new Intent(this, MainActivity.class);
        //extract the text from the EditText
        String message = textView.getText().toString();
        //place text as an Extra in an Intent
        intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
        intent.putExtra(MainActivity.EXTRA_LINE_COUNT, ""+ countLines(message));
        intent.putExtra(MainActivity.EXTRA_CHAR_COUNT, ""+ countCharacters(message));
        intent.putExtra(MainActivity.EXTRA_WORD_COUNT, ""+ countWords(message));
        //pass text to the first activity MAinActivity
        setResult(RESULT_OK, intent);
        finish();
    }

    //Called when the user taps the Cancel button
    protected void onClickCancel(View view) {
        //creating intent
        Intent intent = new Intent(this, MainActivity.class);
        //extract the text from the EditText
        String message = textView.getText().toString();
        //place text as an Extra in an Intent
        intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
        //pass text to the first activity MAinActivity
        setResult(RESULT_CANCELED);
        finish();
    }

    //count characters method
    public static int countCharacters(String message) {
        final String onlyLetters = message.replaceAll("[^\\p{L}]", "");
        return onlyLetters.length();
    }

    //count words method
    public static int countWords(String message){
        message = message.trim();
        if (message.isEmpty())
            return 0;
        return message.split("\\s+").length; // separate string around spaces
    }

    //count lines method
    private static int countLines(String message){
        String[] lines = message.split("\r\n|\r|\n");
        return lines.length;
    }
}
