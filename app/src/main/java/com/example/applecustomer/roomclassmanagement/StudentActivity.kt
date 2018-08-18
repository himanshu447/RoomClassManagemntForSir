package com.example.applecustomer.roomclassmanagement

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.applecustomer.roomclassmanagement.classes.ClassesViewModel
import com.example.applecustomer.roomclassmanagement.dialog.updateDialg
import com.example.applecustomer.roomclassmanagement.student.Student

import kotlinx.android.synthetic.main.activity_add_new_student.*

private val TAG = StudentActivity::class.java.simpleName
private const val REQUEST_CODE_FOR_STUDENT = 1
private val DIALOG_TAG = "$TAG.DIALOG_TAG"

class StudentActivity : AppCompatActivity(), updateDialg.CallBack {


    private var studentViewModel: ClassesViewModel? = null

    private var cid: Int? = null
    val fragment = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)
        setSupportActionBar(toolbar)

        cid = intent.getIntExtra("cid", 0)


        val recy = findViewById<RecyclerView>(R.id.recyclerStudent)
        val adapater = StudentAdapter(this)

        studentViewModel = ViewModelProviders.of(this).get(ClassesViewModel::class.java)

        studentViewModel!!.getallStudent(cid!!).observe(this, Observer<List<Student>> {
            if (it != null)
                adapater.setStudent(it)
        })

        recy.layoutManager = LinearLayoutManager(this)
        recy.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recy.adapter = adapater

        fab.setOnClickListener {
            val i = Intent(this, AddNewStudentActivity::class.java)
            startActivityForResult(i, REQUEST_CODE_FOR_STUDENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_CODE_FOR_STUDENT && resultCode == Activity.RESULT_OK && data != null) {
            val sname = data.getStringExtra("student_name")
            val student = Student(sname, cid)
            studentViewModel?.insetStudent(student)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onUpdate(name: String, sid: Int?) {
        if (sid != null) {
            studentViewModel?.updateStudent(sid, name)
        }
    }

    override fun onDelete(sid: Int?) {
        if (sid != null)
            studentViewModel?.deleteStudent(sid)
    }


    inner class StudentVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sname = itemView.findViewById<TextView>(R.id.studentNameTV)

        fun bindData(student: Student) {
            itemView.setOnClickListener {
                val dialg = updateDialg.newInstance(student.name, student.sid)
                dialg.show(fragment, DIALOG_TAG)
            }
            sname.text = student.name
        }
    }

    inner class StudentAdapter(val context: Context) : RecyclerView.Adapter<StudentVH>() {
        private var list: List<Student> = ArrayList()
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudentVH {
            val v = LayoutInflater.from(context).inflate(R.layout.item_student, p0, false)
            return StudentVH(v)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(p0: StudentVH, p1: Int) {
            val student: Student = list[p1]
            p0.bindData(student)
        }

        fun setStudent(student: List<Student>) {
            list = student
            notifyDataSetChanged()
        }
    }


}
