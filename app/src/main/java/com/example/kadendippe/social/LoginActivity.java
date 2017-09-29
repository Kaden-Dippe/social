package com.example.kadendippe.social;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    protected FirebaseAuth mAuth;

    protected FirebaseAuth.AuthStateListener mAuthListener;


    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        context = this.getApplicationContext();

        //login button
        Button logIn = (Button) findViewById(R.id.LogIn);
        //sign up button
        Button signUp = (Button) findViewById(R.id.SignUp);

        signUp.setOnClickListener(this);

        logIn.setOnClickListener(this);


        logIn.setText("Log In");
        signUp.setText("Sign Up");


        //tracking users signing in and out
        mAuth = FirebaseAuth.getInstance();

        //listening for people trying to log in
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Firebase", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent i = new Intent(LoginActivity.this, MainFeed.class);
                    startActivity(i);

                } else {
                    // User is signed out
                    Log.d("Firebase", "onAuthStateChanged:signed_out");
                }
            }
        };



    }

        //trying to log in
        private void attemptLogin(){

        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

            if(email.length() > 3 && password.length() > 5) {

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("Firebase", "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w("Firebase", "signInWithEmail:failed", task.getException());
                                    Toast.makeText(LoginActivity.this, "Sign in Failed",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    //proceed to the main feed
                                    Intent i = new Intent(LoginActivity.this, MainFeed.class);
                                    startActivity(i);
                                }

                                // ...
                            }
                        });
            } else {
                Toast.makeText(LoginActivity.this, "password must be at least 5 characters ",
                        Toast.LENGTH_SHORT).show();
                Log.d("WTFBRO", "Log IN Not Working");

            }
        }




    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick( View view) {

        Log.d("onClick", "reached the onclick method" );
        switch(view.getId()){
            case R.id.LogIn:
                attemptLogin();
                break;
            case R.id.SignUp:
                Intent signUp = new Intent(context,signUp.class);
                startActivity(signUp);
                break;
        }
    }
}
