package com.example.kadendippe.social;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

/**
 * Created by kadendippe on 10/6/17.
 */

public class FirebaseUtils {

    static FirebaseAuth mAuth = FirebaseAuth.getInstance();




    public static void Transaction(DatabaseReference ref, CheckBox c, TextView t) {


        final TextView rvsp = t;
        final CheckBox check = c;

        ref.runTransaction(new Transaction.Handler() {

            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

               Event event = mutableData.getValue(Event.class);

               //if checkbox is now unchecked, then subtract 1 from _rvsp
               if (check.isChecked()) {
                   event._rvsp += 1;
                   event._signups.add(mAuth.getCurrentUser().getEmail());
                   //if checkbox now checked, add 1 to _rvsp
               } else if (!check.isChecked()) {
                   event._rvsp -= 1;
                   event._signups.remove(mAuth.getCurrentUser().getEmail());
               }
               mutableData.setValue(event);
               rvsp.setText("rvsp: " + event._rvsp + "");
               return Transaction.success(mutableData);
            }
            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
               Log.d("Firebase", "postTransaction:onComplete:" + databaseError);
            }
        });

    }
}
