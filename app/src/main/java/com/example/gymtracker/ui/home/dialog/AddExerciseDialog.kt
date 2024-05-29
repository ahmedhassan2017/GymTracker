package com.example.gymtracker.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.gymtracker.Models.Exercise
import com.example.gymtracker.databinding.DialogAddExerciseBinding

class AddExerciseDialog(private val listener: (Exercise) -> Unit) : DialogFragment() {

    private var _binding: DialogAddExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val sets = binding.etSets.text.toString()
            val reps = binding.etReps.text.toString()
            val weight = binding.etWeight.text.toString()

            val exercise = Exercise(name, sets, reps, weight)
            listener(exercise)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
