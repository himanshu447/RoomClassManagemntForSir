package com.example.applecustomer.roomclassmanagement.classes

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "class_table")
class Classes {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "class_id")
    var id: Int? = null

    @NonNull
    @ColumnInfo(name = "class_name")
    var name: String? = null

    @NonNull
    @ColumnInfo(name = "class_descripition")
    var description: String? = null


    constructor(name: String?, description: String?) {
        this.name = name
        this.description = description
    }

}