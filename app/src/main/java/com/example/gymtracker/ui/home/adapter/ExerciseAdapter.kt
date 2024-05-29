package com.example.gymtracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtracker.Models.Exercise
import com.example.gymtracker.databinding.ItemExerciseBinding

class ExerciseAdapter(private val exercises: List<Exercise>) :
        RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        with(holder.binding) {
            exerciseName.text = exercise.name
            exerciseDetails.text = "Sets: ${exercise.sets}, Reps: ${exercise.reps}, Weight: ${exercise.weight}kg"
        }
    }

    override fun getItemCount(): Int = exercises.size
}
