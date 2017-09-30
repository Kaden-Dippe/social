package com.example.kadendippe.social;

/**
 * Created by kadendippe on 9/29/17.
 */

public class Event {

/*

Email of member who posted, name of event, picture of event, number of people who RSVP’d “Interested”,
in addition to a description of the event

 */

    public String _memberEmail;
    String _name;
    String _date;
    //String _imageUrL;
    String _description;
    int _rvsp;
    String _id;

    public Event() {

    }

    public Event(String memberEmail, String name, String description, String date, int rvsp, String id){
        this._memberEmail = memberEmail;
        this._name = name;
        this._date = date;
        //_imageUrL = imageUrl;
        this._description = description;
        this._rvsp = rvsp;
        this._id = id;
    }





    //getters
    public String get_date() {
        return _date;
    }

    public String get_memberEmail() {
        return _memberEmail;
    }

    public String get_name() {
        return _name;
    }

    public String get_description() {
        return _description;
    }

    public int get_rvsp() {
        return _rvsp;
    }

    public String get_id() {return _id; }





}
