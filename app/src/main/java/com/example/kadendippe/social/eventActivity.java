package com.example.kadendippe.social;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import static android.R.attr.data;

public class eventActivity extends AppCompatActivity implements View.OnClickListener{


    DatabaseReference ref;

    Event event;

    Context context;

    String id;

    CheckBox interested;

    FirebaseAuth.AuthStateListener mAuth;

    TextView rvsp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        interested = (CheckBox) findViewById(R.id.interested);
        interested.setOnClickListener(this);
        TextView email = (TextView) findViewById(R.id.email);
        rvsp = (TextView) findViewById(R.id.rvsp);
        TextView date = (TextView) findViewById(R.id.date);
        TextView name = (TextView) findViewById(R.id.name);
        TextView description = (TextView) findViewById(R.id.description);

        context = this.getApplicationContext();


        Bundle intent = getIntent().getExtras();

        id = intent.getString("id");
        final String emailz = intent.getString("email");
        final String namez = intent.getString("name");
        final String datez = intent.getString("date");
        final int rvspz = intent.getInt("rvsp: ", 0);
        final String descriptionz = intent.getString("description");


        ref = FirebaseDatabase.getInstance().getReference("Events/" + id);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Event event = snapshot.getValue(Event.class);
                 if(event._signups.contains(mAuth.getCurrentUser().getEmail())){
                    interested.setChecked(true);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        //setting all the textviews and buttonview
        email.setText(emailz);
        rvsp.setText("Rvsp: " + String.valueOf(rvspz));
        date.setText("date: " + datez);
        name.setText(namez);
        description.setText(descriptionz);
        interested.setText("Going");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.interested):
                FirebaseUtils.Transaction(ref, interested, rvsp);
                break;

        }
    }
}