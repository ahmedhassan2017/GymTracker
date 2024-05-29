package com.example.gymtracker.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtracker.data.AppDataBase
import com.example.gymtracker.databinding.FragmentHistoryBinding
import com.example.gymtracker.ui.home.adapter.ExerciseAdapter
import com.example.gymtracker.viewmodel.ExerciseViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HistoryFragment : Fragment()
{

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val viewModel: ExerciseViewModel by viewModels()
    var appDataBase: AppDataBase? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupObservers()
        exerciseAdapter = ExerciseAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = exerciseAdapter
        }
        appDataBase = AppDataBase.getINSTANCE(requireContext())
        viewModel.getExercisesByDate(appDataBase, getCurrentDate())



        return root
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

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}