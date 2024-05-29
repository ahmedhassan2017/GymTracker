package com.example.gymtracker.Models

// data/Exercise.kt
// app/src/main/java/com/example/gymtracker/data/Exercise.kt

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(

        val name: String,
        val sets: String,
        val reps: String,
        val weight: String,
        val date: String

){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
