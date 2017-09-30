package com.example.kadendippe.social;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newEventActivity extends AppCompatActivity {

    DatabaseReference ref;

    public EditText name;
    public EditText date;
    public EditText description;


    protected FirebaseAuth mAuth;

    public Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //getting context
        context = this.getApplicationContext();

        //getReference tells you what node you are getting
        ref = FirebaseDatabase.getInstance().getReference("Events");

        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);

        description = (EditText) findViewById(R.id.description);




        //click on image view should lead to camera roll intent

        //name, description, date


        Button add = (Button) findViewById(R.id.add);

        add.setText("Create Event!");

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addEvent();

            }
        });
    }


    //adding event
    private void addEvent() {

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String id = ref.push().getKey();

        String n = name.getText().toString();
        String d = date.getText().toString();
        String des = description.getText().toString();

        Event event = new Event(user.getEmail(),n,des,d,0,id);
        //put new event into the database
        ref.child(id).setValue(event);

        //get back to main feed
        Intent i = new Intent(context, MainFeed.class);

        startActivity(i);
    }
}
