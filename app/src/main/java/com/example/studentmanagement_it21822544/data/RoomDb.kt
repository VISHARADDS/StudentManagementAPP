package com.example.studentmanagement_it21822544.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [StudentEntity::class,ResultEntity::class], version = 1)
abstract class RoomDb:RoomDatabase() {

    abstract fun studentDao():StudentDao ?
    abstract fun resultsDao(): ResultsDao ?

    companion object {



        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getAppDatabase(context: Context): RoomDb {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "AppDB"
                ).allowMainThreadQueries()
                    .build().also {
                        INSTANCE = it
                    }
            }

        }
    }
}


