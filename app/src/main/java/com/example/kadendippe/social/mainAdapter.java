package com.example.kadendippe.social;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by kadendippe on 9/29/17.
 */

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.CustomViewHolder> {



    Context context;
    ArrayList<Event> Events  = new ArrayList<>();
    public mainAdapter(Context context, ArrayList Event) {
        this.context = context;
        //creating a copy of an array
        this.Events = new ArrayList<Event>(Event);
    }


    public mainAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    /* YOUR CODE HERE */
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Event event = Events.get(position);
        //glide library stuff
        //holder.image.setImageResource();

        holder.name.setText(event._name);
        holder.rvsp.setText("Rvsp: " + String.valueOf(event._rvsp));
        holder.email.setText(event._memberEmail);
    }


    public int getItemCount() {
        return Events.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView rvsp;
        TextView email;
        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            rvsp = (TextView) view.findViewById(R.id.rvsp);
        }
    }


}


