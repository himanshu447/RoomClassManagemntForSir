package com.example.applecustomer.roomclassmanagement.classes

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ClassesDOA {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun classesInsert(classes: Classes?)

    @Query("select * from class_table order by class_name")
    fun getAllClasses() : LiveData<List<Classes>>


}