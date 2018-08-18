package com.example.applecustomer.roomclassmanagement.classes

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class ClassesRespositery(application: Application) {

    private var classesDOA: ClassesDOA? = null
    private var allClasses: LiveData<List<Classes>>? = null

    init {
        val classRoomDB = ClassesRoomDatabase.getDataBase(application)
        classesDOA=classRoomDB?.classesDOA()
        allClasses=classesDOA?.getAllClasses()
    }

    fun getAllClasses() : LiveData<List<Classes>>? {
       return allClasses
    }

    fun insert(classes: Classes)
    {
         insertClassAscy(classesDOA!!).execute(classes)
    }

    class insertClassAscy(classesDOA: ClassesDOA) : AsyncTask<Classes, Void, Void>()
    {
        private var mdoa : ClassesDOA?= classesDOA

        override fun doInBackground(vararg p0: Classes?): Void? {
            mdoa?.classesInsert(p0[0])
            return null
        }

    }
}