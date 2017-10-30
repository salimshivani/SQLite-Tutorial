package com.mohammedsalim.sqlitetutorial.java;

/**
 * Created by SULTAN on 28-10-2017.
 */

public class Contact {

    private int _id;
    private String _name;
    private String _phone_number;

    //Empty Constructor
    Contact() {

    }

    //Constructor
    public Contact(String name, String phone_number) {
        name = name;
        phone_number = phone_number;
    }

    //Constructor
    public Contact(int id, String name, String phone_number) {
        id = id;
        name = name;
        phone_number = phone_number;
    }

    //Getting ID
    public int get_id() {
        return _id;
    }

    //Setting ID
    public void set_id(int _id) {
        this._id = _id;
    }

    //Getting Name
    public String get_name() {
        return _name;
    }

    //Setting Name
    public void set_name(String _name) {
        this._name = _name;
    }

    //Getting Phone Number
    public String get_phone_number() {
        return _phone_number;
    }

    //Setting Phone Number
    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

}
