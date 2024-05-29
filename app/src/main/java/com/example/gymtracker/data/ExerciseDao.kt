package com.example.gymtracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymtracker.Models.Exercise
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ExerciseDao {
 @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Completable

    @Query("SELECT * FROM exercises WHERE date = :date")
    fun getExercisesByDate(date: String): Single<List<Exercise>>
}
