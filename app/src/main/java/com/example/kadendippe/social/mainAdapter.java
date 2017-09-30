package com.example.kadendippe.social;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by kadendippe on 9/29/17.
 */

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.CustomViewHolder> {



    String a;

    Context context;
    public mainAdapter(Context context) {
        this.context = context;
        //creating a copy of an array
    }


    public mainAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    /* YOUR CODE HERE */
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        Event event = MainFeed.events.get(position);
        //glide library stuff
        //holder.image.setImageResource();

        holder.name.setText(event.get_name());
        holder.rvsp.setText("Rvsp: " + String.valueOf(event.get_rvsp()));
        holder.email.setText(event.get_memberEmail());
        holder.id = event.get_id();

        Log.e("Error",holder.id);

        //id String to get passed in

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                Intent i = new Intent(context, eventActivity.class);
                i.putExtra("id for firebase", holder.id);
                context.startActivity(i);

            }
        });

    }


    public int getItemCount() {

        return MainFeed.events.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView rvsp;
        TextView email;
        String id;
        public CustomViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            rvsp = (TextView) view.findViewById(R.id.rvsp);
        }
    }


}


