package com.example.akshitagupta.project01;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int contactEntry = 0;   //a variable to check the validity of the name entered
    String name ="";       //variable to store the name entered
    Button create;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = findViewById(R.id.button);       //"Create Contact" button
        edit = findViewById(R.id.button2);       //"Edit Contact" button

        //when create button is clicked
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent,1);   //1  is the original code identifying the call
            }

        });

        //when edit button is clicked
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contactEntry == 0)     //User cannot proceed without entering a name
                    Toast.makeText(MainActivity.this, "Please Enter a Name First" , Toast.LENGTH_LONG).show();   //display message in case user doesn't enter a name
                else if(contactEntry == -1)
                    Toast.makeText(MainActivity.this, name+" is Not a Valid Name" , Toast.LENGTH_LONG).show();   //display toast message in case an incorrect name is entered
                else if(contactEntry == 1){
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);   //Creating Intent for invoking Contact activity
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                    startActivity(intent);     //Starting Contact activity
                }
            }
        });
    }

    //function to get the result from SecondActivity
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){                         //requestCode == 1  is the original code identifying the call
            //if(data!=null)
                name = data.getStringExtra("name");
            if(resultCode == RESULT_OK){             //if resultCode is RESULT_OK, contactEntry is set to 1 & contact activity is invoked
                contactEntry = 1;
            }
            else if(resultCode == RESULT_CANCELED)  //if resultCode is RESULT_CANCELED, contactEntry is set ot -1 & display the toast specified above
                contactEntry = -1;
        }
    }
}
