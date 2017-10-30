package com.mohammedsalim.sqlitetutorial.kotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import java.util.ArrayList

/**
 * Created by SULTAN on 28-10-2017.
 */
class DatabaseHandler(ctx: Context) :
        ManagedSQLiteOpenHelper(ctx, "Contacts", null, 1) {

    //Static variables
    //Database version
    private val DATABASE_VERSION = 1

    //Database name
    private val DATABASE_NAME = "ContactsManager"

    //Table name (Contacts)
    private val TABLE_CONTACTS = "Contacts"

    //Table fields
    private val KEY_ID = "id"
    private val KEY_NAME = "name"
    private val KEY_PH_NO = "phone_number"

    companion object {
        private var instance: DatabaseHandler? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHandler {
            if (instance == null) {
                instance = DatabaseHandler(ctx.applicationContext)
            }
            return instance!!
        }
    }
    
    /*constructor(context: Context) : this(context, DATABASE_NAME, null, DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION)
    }*/

    //Creating tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")")

        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    //Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop if older table exits
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)

        //Create table again
        onCreate(db)
    }

    // Adding new contact
    fun addContact(contact: Contact) {

        val db = this.getWritableDatabase()

        val values = ContentValues()
        values.put(KEY_NAME, contact.name) // Contact Name
        values.put(KEY_PH_NO, contact.phone_number) // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values)
        db.close() // Closing database connection
    }

    // Getting single contact
    fun getContact(id: Int): Contact {
        val db = this.getWritableDatabase()

        val cursor = db.query(TABLE_CONTACTS,
                arrayOf(KEY_ID, KEY_NAME, KEY_PH_NO),
                KEY_ID + "=?",
                arrayOf(id.toString()), null, null, null, null)

        if (cursor != null) {
            cursor!!.moveToFirst()
        }

        return Contact(Integer.parseInt(
                cursor!!.getString(0)),
                cursor!!.getString(1),
                cursor!!.getString(2))
    }

    // Getting All Contacts
    fun getAllContacts(): List<Contact> {
        val contactList = ArrayList<Contact>()
        //Select All Query
        val selectQuery = "SELECT  * FROM " + TABLE_CONTACTS

        val db = this.getWritableDatabase()
        val cursor = db.rawQuery(selectQuery, null)

        //Looping through all rows and adding to list.
        if (cursor.moveToFirst()) {
            do {
                val contact = Contact()
                contact.set_id(Integer.parseInt(cursor.getString(0)))
                contact.name = cursor.getString(1)
                contact.phone_number = cursor.getString(2)

                //Adding contact to list.
                contactList.add(contact)
            } while (cursor.moveToNext())
        }

        //Return Contact List
        return contactList
    }

    // Getting contacts Count
    fun getContactsCount(): Int {
        val countQuery = "SELECT  * FROM " + TABLE_CONTACTS
        val db = this.getReadableDatabase()
        val cursor = db.rawQuery(countQuery, null)
        cursor.close()

        //Return Count
        return cursor.getCount()
    }

    // Updating single contact
    fun updateContact(contact: Contact): Int {
        val db = this.getWritableDatabase()

        val values = ContentValues()
        values.put(KEY_NAME, contact.name)
        values.put(KEY_PH_NO, contact.phone_number)

        //Updating Row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                arrayOf(contact.get_id().toString()))
    }

    // Deleting single contact
    fun deleteContact(contact: Contact) {
        val db = this.getWritableDatabase()
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                arrayOf(contact.get_id().toString()))
        db.close()
    }
}