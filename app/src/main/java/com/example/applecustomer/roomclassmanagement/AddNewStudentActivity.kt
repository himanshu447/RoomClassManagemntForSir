package com.example.applecustomer.roomclassmanagement

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView

class AddNewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student2)

        val button = findViewById<Button>(R.id.studentSaveBTN)
        val nameET = findViewById<TextView>(R.id.studentNameET)

        button.setOnClickListener {
            val i = Intent()
            if (TextUtils.isEmpty(nameET.text))
                setResult(Activity.RESULT_CANCELED, i)
            else {
                i.putExtra("student_name", nameET.text.toString())
                setResult(Activity.RESULT_OK, i)
            }
            finish()
        }
    }
}
