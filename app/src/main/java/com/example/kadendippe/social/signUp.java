package com.example.kadendippe.social;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class signUp extends AppCompatActivity implements View.OnClickListener{



    protected FirebaseAuth mAuth;

    protected FirebaseAuth.AuthStateListener mAuthListener;

    protected Context context;

    protected EditText email;

    protected EditText password;



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

        email = ((EditText) findViewById(R.id.e));

        password = ((EditText) findViewById(R.id.pass));



        //settting edit text text
        //((EditText) findViewById(R.id.e)).setText("Email", TextView.BufferType.EDITABLE);
        //((EditText) findViewById(R.id.pass)).setText("Password", TextView.BufferType.EDITABLE);



    }

    private void attemptSignup(String email,String password) {

        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Firebase", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

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

    private boolean validateForm() {
        boolean valid = true;

        String email = ((EditText) findViewById(R.id.e)).getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(signUp.this, "Please enter an email",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        String password = ((EditText) findViewById(R.id.pass)).getText().toString();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(signUp.this, "Please enter a password",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    public void onClick(View v){
        switch(v.getId()) {
            case R.id.sex:
                String email = ((EditText) findViewById(R.id.e)).getText().toString();
                String password = ((EditText) findViewById(R.id.pass)).getText().toString();
                attemptSignup(email,password);
                break;
        }



    }




}