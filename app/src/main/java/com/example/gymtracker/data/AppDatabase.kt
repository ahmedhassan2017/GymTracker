package com.example.gymtracker.data

// app/src/main/java/com/example/gymtracker/data/AppDatabase.kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymtracker.Models.Exercise

@Database(entities = [Exercise::class], version = 5)
abstract class AppDataBase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao?

    companion object {
        private var INSTANCE: AppDataBase? = null

        @Synchronized
        fun getINSTANCE(context: Context): AppDataBase?
        {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, "exercise"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }
}