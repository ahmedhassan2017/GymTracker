package com.example.gymtracker.data

import androidx.lifecycle.LiveData
import com.example.gymtracker.Models.Exercise
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    fun insert(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }

//    fun getExercisesByDate(date: Long): Single<List<Exercise>> {
//        exerciseDao.getExercisesByDate(date).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<List<Exercise>>{
//            override fun onSubscribe(d: Disposable)
//            {
//
//            }
//
//            override fun onError(e: Throwable)
//            {
//            }
//
//            override fun onSuccess(t: List<Exercise>)
//            {
//            }
//        })
//    }
}
