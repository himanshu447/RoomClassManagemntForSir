package com.example.applecustomer.roomclassmanagement.student

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class StudentViewModel(application: Application) : AndroidViewModel(application)  {

    val studentRespositey = StudentRespositery(this.getApplication())

    fun getallStudent(selectedid: Int): LiveData<List<Student>> {
        val studentList = studentRespositey.getallStudents(selectedid)
        return studentList!!
    }

    fun insetStudent(student: Student) {
        studentRespositey.insert(student)
    }
}