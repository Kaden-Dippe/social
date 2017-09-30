package com.example.kadendippe.social;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class eventActivity extends AppCompatActivity {

    DatabaseReference ref;

    Event event;

    Context context;



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

        context = this.getApplicationContext();


        Intent intent = getIntent();

        ref = FirebaseDatabase.getInstance().getReference("Events");



        final String id = intent.getStringExtra("id");
        final String emailz = intent.getStringExtra("email");
        final String namez = intent.getStringExtra("name");
        final String datez = intent.getStringExtra("date");
        final int rvspz = intent.getIntExtra("rvsp", 0);
        final String descriptionz = intent.getStringExtra("description");


        ref = FirebaseDatabase.getInstance().getReference("Events");



        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //setting all the textviews and buttonview
        email.setText(emailz);
        rvsp.setText("Rvsp: " + String.valueOf(rvspz));
        date.setText("date: " + datez);
        name.setText(namez);
        //description.setText(descriptionz);
        interested.setText("I'm in!!");

        interested.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Event event = new Event(emailz, namez, descriptionz, datez, rvspz, id,"");
                //put new event into the database
                ref.child(id).setValue(event);

                //updating rvsp of current event object

                ref.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    Event eventz = snapshot.getValue(Event.class);

                    if (!eventz.get_signups().contains(mAuth.getCurrentUser().getEmail())) {
                        ref.child(id).setValue(new Event(emailz, namez, descriptionz, datez, rvspz + 1, id,mAuth.getCurrentUser().getEmail()));

                    } else {
                        Toast.makeText(eventActivity.this, "You all already interested",
                                Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
                Intent i = new Intent(context, MainFeed.class);
                startActivity(i);
            }
        });

    }


}