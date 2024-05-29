package com.example.gymtracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtracker.Models.Exercise
import com.example.gymtracker.databinding.ItemExerciseBinding

class ExerciseAdapter : ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(ExerciseDiffCallback()) {

    class ExerciseViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position)
        with(holder.binding) {
            exerciseName.text = exercise.name
            exerciseDetails.text = "Sets: ${exercise.sets}, Reps: ${exercise.reps}, Weight: ${exercise.weight}kg"
        }
    }
}

class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        // يمكنك تعديل هذه الطريقة للتحقق من الهوية الفريدة للتمارين إذا كانت متاحة
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}
