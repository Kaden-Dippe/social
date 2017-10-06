package com.example.kadendippe.social;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by kadendippe on 10/6/17.
 */

public class Utils {


    static class downloadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String name;

        public downloadImage(Bitmap image, String name){
            this.image = image;
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }


    static boolean validateForm(String email, String password, Context C) {
        boolean valid = true;


        if (TextUtils.isEmpty(email) || email.length() < 5 || !email.contains("@")) {
            Toast.makeText(C, "Please enter a valid email",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if (TextUtils.isEmpty(password) || password.length() < 5) {
            Toast.makeText(C, "Password must be at least 5 characters",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }
}
