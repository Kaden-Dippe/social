package com.example.kadendippe.social;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class eventActivity extends AppCompatActivity {

    DatabaseReference ref;

    Event event;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        Button interested = (Button) findViewById(R.id.interested);
        TextView email = (TextView) findViewById(R.id.email);
        TextView rvsp = (TextView) findViewById(R.id.rvsp);
        TextView date = (TextView) findViewById(R.id.date);
        TextView name = (TextView) findViewById(R.id.name);
        TextView description = (TextView) findViewById(R.id.description);


        Intent intent = getIntent();




        final String id = intent.getStringExtra("id");
        final String emailz = intent.getStringExtra("email");
        final String namez = intent.getStringExtra("name");
        final String datez = intent.getStringExtra("date");
        final int rvspz = intent.getIntExtra("rvsp", 0);
        final String descriptionz = intent.getStringExtra("description");


        ref = FirebaseDatabase.getInstance().getReference("Events");



        //setting all the textviews and buttonview
        email.setText(emailz);
        rvsp.setText("Rvsp: " + String.valueOf(rvspz));
        date.setText(datez);
        name.setText(namez);
        //description.setText(descriptionz);
        interested.setText("I'm in!!");

        interested.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                ref.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Event event = snapshot.getValue(Event.class);
                    event._rvsp += 1;
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });*/

            }
        });

    }


}