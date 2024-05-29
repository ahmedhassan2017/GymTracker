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
import com.example.gymtracker.databinding.FragmentHomeBinding

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

        exerciseAdapter = ExerciseAdapter(exercises)
       binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = exerciseAdapter
        }

      binding.btnAddExercise.setOnClickListener {
            // هنا هنضيف الكود لإضافة تمرين جديد
          AddExerciseDialog { exercise ->
              exercises.add(exercise)
              exerciseAdapter.notifyDataSetChanged()
          }.show(requireActivity().supportFragmentManager, "AddExerciseDialog")
        }



        return binding.root
    }


    // adapter/ExerciseAdapter.kt
    class ExerciseAdapter(private val exercises: List<Exercise>) :
            RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

        class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.exerciseName)
            val details: TextView = view.findViewById(R.id.exerciseDetails)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_exercise, parent, false)
            return ExerciseViewHolder(view)
        }

        override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
            val exercise = exercises[position]
            holder.name.text = exercise.name
            holder.details.text = "Sets: ${exercise.sets}, Reps: ${exercise.reps}, Weight: ${exercise.weight}kg"
        }

        override fun getItemCount(): Int = exercises.size
    }




    // AddExerciseDialog.kt
    class AddExerciseDialog(private val listener: (Exercise) -> Unit) : DialogFragment() {

        override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.dialog_add_exercise, container, false)
            view.findViewById<Button>(R.id.btnAdd).setOnClickListener {
                val name = view.findViewById<EditText>(R.id.etName).text.toString()
                val sets = view.findViewById<EditText>(R.id.etSets).text.toString().toInt()
                val reps = view.findViewById<EditText>(R.id.etReps).text.toString().toInt()
                val weight = view.findViewById<EditText>(R.id.etWeight).text.toString().toFloat()

                val exercise = Exercise(name, sets, reps, weight)
                listener(exercise)
                dismiss()
            }
            return view
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}