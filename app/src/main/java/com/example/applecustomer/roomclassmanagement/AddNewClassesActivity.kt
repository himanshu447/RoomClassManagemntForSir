package com.example.applecustomer.roomclassmanagement

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.applecustomer.roomclassmanagement.classes.Classes

class AddNewClassesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_classes)

        val button = findViewById<Button>(R.id.saveClassBtn)
        val nameET = findViewById<TextView>(R.id.classNameET)
        val dewscET = findViewById<TextView>(R.id.classDescET)

        button.setOnClickListener {
            val i = Intent()
            if (TextUtils.isEmpty(nameET.text) && TextUtils.isEmpty(dewscET.text))
                setResult(Activity.RESULT_CANCELED, i)
            else {
                i.putExtra("name", nameET.text.toString())
                i.putExtra("desc", dewscET.text.toString())
                setResult(Activity.RESULT_OK, i)
            }
            finish()
        }

    }


}
