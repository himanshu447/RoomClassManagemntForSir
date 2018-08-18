package com.example.applecustomer.roomclassmanagement.student

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.applecustomer.roomclassmanagement.classes.ClassesRoomDatabase

class StudentRespositery(application: Application) {

    var studentDOA: StudentDOA? = null

    var sid12: Int? = null
    var sname: String? = null

    init {
        val romdb = ClassesRoomDatabase.getDataBase(application)
        studentDOA = romdb?.studentDOA()
    }

    fun getallStudents(selectedCladdId: Int): LiveData<List<Student>>? {
        return studentDOA?.getAllStudent(selectedCladdId)
    }

    fun insert(student: Student) {
        insertStudentAscy(studentDOA!!).execute(student)
    }


    fun updateStudent(sid: Int, name: String) {
        sid12 = sid
        sname = name
        updateStudentAscy(studentDOA!!).execute(name)
    }

    fun deleteStudent(sid: Int) {
        DeleteStudentAsy(studentDOA!!).execute(sid)
    }

    inner class updateStudentAscy(studentDOA: StudentDOA) : AsyncTask<String, Void, Void>() {

        val asydoa: StudentDOA = studentDOA

        override fun doInBackground(vararg p0: String?): Void? {
            asydoa.updateStudent(student_id = sid12!!, name = sname!!)
            return null
        }
    }

    class DeleteStudentAsy(studentDOA: StudentDOA) : AsyncTask<Int,Void,Void>()
    {
        val asydoa: StudentDOA = studentDOA

        override fun doInBackground(vararg p0: Int?): Void? {
           asydoa.deleteStudent(p0[0]!!)
            return null
        }
    }

    class insertStudentAscy(studentDOA: StudentDOA) : AsyncTask<Student, Void, Void>() {

        val asydoa: StudentDOA = studentDOA

        override fun doInBackground(vararg p0: Student?): Void? {

            asydoa.studentInsert(p0[0])
            return null
        }

    }
}