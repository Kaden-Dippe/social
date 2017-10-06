package com.example.kadendippe.social;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

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
    String _imageUri;
    String _description;
    int _rvsp;
    String _id;
    ArrayList<String> _signups = new ArrayList<>();


    public Event() {

    }


/*
    public Event(String memberEmail, String name, String description, String date, int rvsp, String id, String signup){
        this._memberEmail = memberEmail;
        this._name = name;
        this._date = date;
        this._description = description;
        this._rvsp = rvsp;
        this._id = id;
        _signups.add(signup);
    }
*/

    public Event(String memberEmail, String name, String description, String date, int rvsp, String id, String signup, String imageUrL){
        this._memberEmail = memberEmail;
        this._name = name;
        this._date = date;
        this._imageUri = imageUrL;
        this._description = description;
        this._rvsp = rvsp;
        this._id = id;
        _signups.add(signup);
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

    public String get_imageUri(){ return _imageUri; }

    public void set_id(String s){
        _id = s;
    }

    public List<String> get_signups() {
        return _signups;
    }


}
