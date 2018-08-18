package com.example.applecustomer.roomclassmanagement.classes

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.applecustomer.roomclassmanagement.student.Student
import com.example.applecustomer.roomclassmanagement.student.StudentRespositery

class ClassesViewModel(application: Application) : AndroidViewModel(application) {

    private var classesRespositery = ClassesRespositery(application)
    private var studentRespositery = StudentRespositery(application)


    fun getallClasses(): LiveData<List<Classes>> {
        return classesRespositery.getAllClasses()!!
    }

    fun insert(classes: Classes) {
        classesRespositery.insert(classes)
    }


    fun getallStudent(selectedid: Int): LiveData<List<Student>> {
        val studentList = studentRespositery.getallStudents(selectedid)
        return studentList!!
    }

    fun insetStudent(student: Student) {
        studentRespositery.insert(student)
    }

    fun updateStudent(sid: Int, name: String) {
        studentRespositery.updateStudent(sid, name)
    }

    fun deleteStudent(sid: Int) {
        studentRespositery.deleteStudent(sid)

    }
}