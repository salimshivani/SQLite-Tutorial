package com.mohammedsalim.sqlitetutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.mohammedsalim.sqlitetutorial.kotlin.Contact
import com.mohammedsalim.sqlitetutorial.kotlin.DatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val db = DatabaseHandler.getInstance(applicationContext)

//        val db = DatabaseHandler(this)

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..")
        db.addContact(Contact("Ravi", "9100000000"))
        db.addContact(Contact("Srinivas", "9199999999"))
        db.addContact(Contact("Tommy", "9522222222"))
        db.addContact(Contact("Karthik", "9533333333"))

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..")
        val contacts = db.getAllContacts()

        contacts
                .map {
                    "Id: " + it.get_id() + " ,Name: " + it.get_name() + " ,Phone: " + it.get_phone_number()
                    // Writing Contacts to log
                }
                .forEach { Log.d("Name: ", it) }
    }
}
