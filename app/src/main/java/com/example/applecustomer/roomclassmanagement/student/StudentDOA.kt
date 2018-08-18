package com.example.applecustomer.roomclassmanagement.student

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface StudentDOA {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun studentInsert(student: Student?)

    @Query("Select * from student_table where classes_id = :selectedID")
    fun getAllStudent(selectedID: Int): LiveData<List<Student>>

    @Query("Update student_Table set student_name= :name where student_id = :student_id")
    fun updateStudent(student_id: Int, name: String)

    @Query("Delete from student_Table where student_id= :student_id ")
    fun deleteStudent(student_id:Int)

}