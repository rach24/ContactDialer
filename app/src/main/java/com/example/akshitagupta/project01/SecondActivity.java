package com.example.akshitagupta.project01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText name = findViewById(R.id.editText);   //editText for entering the contact name

        name.setOnEditorActionListener(new TextView.OnEditorActionListener () {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if((event!=null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {  //when enter is pressed
                    int resultCode;

                    //Validate contact name
                    if(name.getText().toString().matches("[a-zA-Z]+"+"\\s+"+"[a-zA-Z]+"))
                        resultCode = RESULT_OK;           //if a legal name is entered, set resultCode to RESULT_OK
                    else
                        resultCode = RESULT_CANCELED;    //if incorrect name entered, set resultCode to RESULT_CANCELED

                    Intent intent = new Intent();
                    intent.putExtra("name", name.getText().toString().trim());   //remove extra white spaces
                    setResult(resultCode, intent);
                    finish();
                }
                return false;
            }
        });
    }
}
