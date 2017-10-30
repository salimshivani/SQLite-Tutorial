package com.mohammedsalim.sqlitetutorial.kotlin

/**
 * Created by SULTAN on 28-10-2017.
 */
class Contact {

    var id: Int = 0
    var name: String? = null
    var phone_number: String? = null

    //Empty Constructor
    constructor() {

    }

    //Constructor
    constructor(name: String, phone_number: String) {
        this.name = name
        this.phone_number = phone_number
    }

    //Constructor
    constructor(id: Int, name: String, phone_number: String) {
        this.id = id
        this.name = name
        this.phone_number = phone_number
    }

    //Getting ID
    fun get_id(): Int {
        return this.id
    }

    //Setting ID
    fun set_id(_id: Int) {
        this.id = _id
    }

    //Getting Name
    fun get_name(): String? {
        return name
    }

    //Setting Name
    fun set_name(_name: String) {
        this.name = _name
    }

    //Getting Phone Number
    fun get_phone_number(): String? {
        return phone_number
    }

    //Setting Phone Number
    fun set_phone_number(_phone_number: String) {
        this.phone_number = _phone_number
    }

}