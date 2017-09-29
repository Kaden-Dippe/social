package com.example.kadendippe.social;

/**
 * Created by kadendippe on 9/29/17.
 */

public class Event {

/*

Email of member who posted, name of event, picture of event, number of people who RSVP’d “Interested”,
in addition to a description of the event

 */

    String _memberEmail;
    String _name;
    //String _imageUrL;
    String _description;
    int _rvsp;

    public Event() {

    }

    public Event(String memberEmail, String name, String description, int rvsp){
        _memberEmail = memberEmail;
        _name = name;
        //_imageUrL = imageUrl;
        _description = description;
        _rvsp = rvsp;
    }



}
