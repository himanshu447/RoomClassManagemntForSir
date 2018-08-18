package com.example.applecustomer.roomclassmanagement.classes

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.arch.persistence.db.SupportSQLiteDatabase
import android.support.annotation.NonNull
import com.example.applecustomer.roomclassmanagement.student.Student
import com.example.applecustomer.roomclassmanagement.student.StudentDOA
import android.arch.persistence.room.migration.Migration


@Database(entities = [Classes::class, Student::class], version = 2)

abstract class ClassesRoomDatabase : RoomDatabase() {

    abstract fun classesDOA(): ClassesDOA
    abstract fun studentDOA(): StudentDOA


    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
                // Create the new table

                database.execSQL(
                        "CREATE TABLE IF NOT EXISTS `student_table` " +
                                "(`student_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                "`student_name` TEXT NOT NULL, " +
                                "`classes_id` INTEGER NOT NULL, " +
                                "FOREIGN KEY(`classes_id`) REFERENCES `class_table`(`class_id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
            }
        }

        private var INSTANCE: ClassesRoomDatabase? = null

        fun getDataBase(context: Context): ClassesRoomDatabase? {

            if (INSTANCE == null) {
                synchronized(ClassesRoomDatabase::class.java)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ClassesRoomDatabase::class.java, "class_database")
                            .addCallback(sRoomDatabaseCallback)
                            .addMigrations(MIGRATION_1_2)
                            .build()
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE).execute()
            }
        }
    }

    private class PopulateDbAsync(db: ClassesRoomDatabase?) : AsyncTask<Void, Void, Void>() {

        private val mDao: ClassesDOA = db!!.classesDOA()

        override fun doInBackground(vararg params: Void): Void? {
            //  mDao.deleteAll()
            var word = Classes("Hello", "description say")
            mDao.classesInsert(word)
            word = Classes("World", "descrption heloe")
            mDao.classesInsert(word)
            return null
        }
    }
}
