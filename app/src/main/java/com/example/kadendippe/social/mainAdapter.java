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
import java.util.List;

/**
 * Created by kadendippe on 9/29/17.
 */

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.CustomViewHolder> {



    String a;

    //so that onclick listener can access this list
    public List<Event> Events = MainFeed.events;

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

        /*
        holder.id = event.get_id();
        holder.memberEmail = event.get_memberEmail();
        holder.nameS = event.get_name();
        holder.date = event.get_date();
        holder.rvspS = event.get_rvsp();
        holder.description = event.get_description();
        */

    }

    public int getItemCount() {

        return MainFeed.events.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView rvsp;
        TextView email;
        //info to pass to the next event

        /*
        String id;
        public String memberEmail;
        String nameS;
        String date;
        //String imageUrL;
        String description;
        int rvspS;
        */

        public CustomViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            rvsp = (TextView) view.findViewById(R.id.rvsp);

            view.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {
                    Event event = Events.get(getAdapterPosition());
                    Intent i = new Intent(context, eventActivity.class);
                    i.putExtra("id", event.get_id());
                    i.putExtra("email", event._memberEmail);
                    i.putExtra("name", event._name);
                    i.putExtra("date",  event._date);
                    i.putExtra("rvsp", event._rvsp);
                    i.putExtra("description", event._description);
                    context.startActivity(i);

                }
            });
        }
    }


}


