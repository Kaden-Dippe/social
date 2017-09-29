package com.example.kadendippe.social;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp extends AppCompatActivity {



    protected FirebaseAuth mAuth;

    protected FirebaseAuth.AuthStateListener mAuthListener;

    protected Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setContentView(R.layout.activity_sign_up);

        context = this.getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //tracking users signing in and out
        mAuth = FirebaseAuth.getInstance();

        //signUp button
        Button signUp = (Button) findViewById(R.id.sex);



        //settting edit text text
        //((EditText) findViewById(R.id.e)).setText("Email", TextView.BufferType.EDITABLE);
        //((EditText) findViewById(R.id.pass)).setText("Password", TextView.BufferType.EDITABLE);

        //when you hit signup button
        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.e)).getText().toString();
                String password = ((EditText) findViewById(R.id.pass)).getText().toString();

                attemptLogin(email,password);

            }
        });

    }

    private void attemptLogin(String email,String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Firebase", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        Toast.makeText(signUp.this, "reached attemptLogin",
                                Toast.LENGTH_SHORT).show();

                        if (task.isSuccessful()) {

                            Toast.makeText(signUp.this, "Welcome to the family :)",
                                    Toast.LENGTH_SHORT).show();

                            //if sign in works
                            Intent i = new Intent(context, MainFeed.class);
                            startActivity(i);


                        } else {
                            //if sign up fails
                            Toast.makeText(signUp.this, "Sign up failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}