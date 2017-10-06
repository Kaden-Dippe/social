package com.example.kadendippe.social;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class newEventActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE = 1;

    DatabaseReference ref;

    public EditText name;
    public EditText date;
    public EditText description;
    public ImageView image;
    public Uri imageUri;
    public String downloadUri;


    String id;

    ProgressBar progressBar;

    protected FirebaseAuth mAuth;

    public Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        id = null;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        imageUri = Uri.fromFile(new File(""));
        downloadUri = null;


        //getting context
        context = this.getApplicationContext();

        //getReference tells you what node you are getting
        ref = FirebaseDatabase.getInstance().getReference("Events");

        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        description = (EditText) findViewById(R.id.description);
        image = (ImageView) findViewById((R.id.picture));
        image.setOnClickListener(this);

        //name, description, date

        Button add = (Button) findViewById(R.id.add);

        add.setText("Create Event!");

        add.setOnClickListener(this);
    }

    private void addEvent() {

        mAuth = FirebaseAuth.getInstance();

        final String n = name.getText().toString();
        final String d = date.getText().toString();
        final String des = description.getText().toString();

        final FirebaseUser user = mAuth.getCurrentUser();

        if(id == null) {
            id = ref.push().getKey();
        }

        Event event = new Event(user.getEmail(), n, des, d, 0, id, "", downloadUri);

        ref.child(id).setValue(event);

        Intent i = new Intent(context, MainFeed.class);
        startActivity(i);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.add):
                addEvent();
                break;
            case(R.id.picture):
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            progressBar.setVisibility(View.VISIBLE);
            imageUri = data.getData();


            id = ref.push().getKey();

            StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("Events/" + id);
            mStorageRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            @SuppressWarnings("VisibleForTests") Uri dloadUri = taskSnapshot.getDownloadUrl();
                            downloadUri = dloadUri.toString();
                            progressBar.setVisibility(View.GONE);
                            image.setImageURI(imageUri);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.d(getResources().getString(R.string.firebase), "upload failed");
                        }
                    });
        }
    }



}
