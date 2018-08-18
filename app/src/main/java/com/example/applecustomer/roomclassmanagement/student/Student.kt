package com.example.applecustomer.roomclassmanagement.student

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.example.applecustomer.roomclassmanagement.classes.Classes


@Entity(tableName = "student_table" , foreignKeys = [ForeignKey(entity = Classes::class, parentColumns = arrayOf("class_id"), childColumns = arrayOf("classes_id"), onDelete = CASCADE)])

class Student {

    constructor(name: String?, cid: Int?) {
        this.name = name
        this.cid = cid
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "student_id")
    var sid: Int? = null

    @NonNull
    @ColumnInfo(name = "student_name")
    var name: String? = null

    @NonNull
    @ColumnInfo(name="classes_id")
    var cid:Int?=null
}