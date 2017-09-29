package com.example.kadendippe.social;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kadendippe on 9/29/17.
 */

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.CustomViewHolder> {



    //TODO Question 4
    //Create a constructor with two arguments. What would they be?
    //Hint: You need to know what the ____ is to be able to manipulate the UI. Also need data to display!

    /* YOUR CODE HERE */
    Context context;
    ArrayList<Company> companies  = new ArrayList<>();
    public CompaniesAdapter(Context context, ArrayList companies) {
        this.context = context;
        //creating a copy of an array
        this.companies = new ArrayList<Company>(companies);
    }

    //TODO Question 5
    //Override the onCreateViewHolder method
    //Hint: we want a layout inflater from the context of its parent and inflate the row_view
    //layout from the parent, then pass that into our custom view holder

    /* YOUR CODE HERE */
    public mainAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent, false);
        return new CustomViewHolder(view);
    }

    //TODO Question 6
    //Bind the data to the holder based on the position


    @Override
    /* YOUR CODE HERE */
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Company company = companies.get(position);
        holder.textView.setText(company.companyName);
        holder.ratingBar.setRating(company.likeCompany);
        holder.imageView.setImageResource(company.imageResId);
    }


    //TODO Question 7
    //Override the item size method

    /* YOUR CODE HERE */
    public int getItemCount() {
        return companies.size();
    }


    //TODO Question 8
    //Create a CustomViewHolder class that extends the base RecyclerView.ViewHolder
    //Override its constructor class with the following signature:
    //CustomViewHolder(View view) {
    //    super(view);
    //    ...
    //}
    //and create instance variables for the UI elements in the layout file
    //Hint: findViewById is a method of the View class

    /* YOUR CODE HERE */



    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RatingBar ratingBar;
        TextView textView;
        public CustomViewHolder(View view) {

            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);

            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            textView = (TextView) view.findViewById(R.id.textView);

        }
    }


}


