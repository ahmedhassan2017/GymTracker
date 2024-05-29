package com.example.gymtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtracker.Models.Exercise
import com.example.gymtracker.R
import com.example.gymtracker.databinding.DialogAddExerciseBinding
import com.example.gymtracker.databinding.FragmentHomeBinding
import com.example.gymtracker.databinding.ItemExerciseBinding
import com.example.gymtracker.ui.home.adapter.ExerciseAdapter
import com.example.gymtracker.ui.home.dialog.AddExerciseDialog

class HomeFragment : Fragment()
{
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val exercises = mutableListOf<Exercise>()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        exerciseAdapter = ExerciseAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = exerciseAdapter
        }

        binding.btnAddExercise.setOnClickListener {
            // هنا هنضيف الكود لإضافة تمرين جديد
            AddExerciseDialog { exercise ->
                exercises.add(exercise)
                exerciseAdapter.submitList(exercises.toList())
            }.show(requireActivity().supportFragmentManager, "AddExerciseDialog")
        }



        return binding.root
    }


    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}