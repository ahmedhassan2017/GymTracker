package com.example.gymtracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymtracker.Models.Exercise
import com.example.gymtracker.data.AppDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ExerciseViewModel : ViewModel()
{

    val data = MutableLiveData<List<Exercise>>()
    val error = MutableLiveData<String>()
    val doneProcess = MutableLiveData<String>()
    private lateinit var disposable: Disposable



    fun insert(appDataBase: AppDataBase? , exercise: Exercise)
    {
        appDataBase?.exerciseDao()?.insert(exercise)?.subscribeOn(Schedulers.computation())?.subscribe(object : CompletableObserver
        {
            override fun onSubscribe(d: Disposable)
            {
                disposable = d
            }

            override fun onComplete()
            {
                doneProcess.postValue("Exercise Cashed ")
            }

            override fun onError(e: Throwable)
            {
                error.postValue(e.message)
                disposable.dispose()
            }
        })
    }

    fun getExercisesByDate(appDataBase: AppDataBase?, date: String)
    {
        appDataBase?.exerciseDao()?.getExercisesByDate(date)?.
        subscribeOn(Schedulers.computation())?.
        observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<List<Exercise>>
            {
                override fun onSubscribe(d: Disposable)
                {
                    disposable = d
                }

                override fun onError(e: Throwable)
                {
                    error.postValue(e.message)
                    disposable.dispose()
                }

                override fun onSuccess(t: List<Exercise>)
                {
                    data.postValue(t)
                }
            })


    }
}
