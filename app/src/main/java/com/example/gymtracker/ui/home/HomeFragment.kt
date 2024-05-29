package com.example.gymtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtracker.Models.Exercise
import com.example.gymtracker.data.AppDataBase
import com.example.gymtracker.databinding.FragmentHomeBinding
import com.example.gymtracker.ui.home.adapter.ExerciseAdapter
import com.example.gymtracker.ui.home.dialog.AddExerciseDialog
import com.example.gymtracker.viewmodel.ExerciseViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment()
{
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val exercises = mutableListOf<Exercise>()
    private var _binding: FragmentHomeBinding? = null


    private val viewModel: ExerciseViewModel by viewModels()
    var appDataBase: AppDataBase? = null


    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        exerciseAdapter = ExerciseAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = exerciseAdapter
        }

        binding.btnAddExercise.setOnClickListener {
            AddExerciseDialog { exercise ->
                exercises.add(exercise)
                exerciseAdapter.submitList(exercises.toList())
                saveExerciseToDatabase(exercise)
            }.show(requireActivity().supportFragmentManager, "AddExerciseDialog")
        }


        appDataBase = AppDataBase.getINSTANCE(requireContext())
        viewModel.getExercisesByDate(appDataBase, getCurrentDate())

        setupObservers()

        return binding.root
    }
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun setupObservers()
    {
        viewModel.data.observe(viewLifecycleOwner) {
            exerciseAdapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

        }
        viewModel.doneProcess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }


    }

    private fun saveExerciseToDatabase(exercise: Exercise)
    {

        viewModel.insert(appDataBase, exercise)

    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}
