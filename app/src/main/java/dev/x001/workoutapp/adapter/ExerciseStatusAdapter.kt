package dev.x001.workoutapp.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.x001.workoutapp.ExerciseModel
import dev.x001.workoutapp.R
import dev.x001.workoutapp.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

        class ViewHolder(binding: ItemExerciseStatusBinding):
            RecyclerView.ViewHolder(binding.root){
            val itemTextView = binding.itemTextView
            val itemCheckLottie = binding.itemCheckLottie
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.itemTextView.text = model.id.toString()

        when {
            model.isCompleted -> {
                holder.itemTextView.visibility = View.INVISIBLE
                holder.itemCheckLottie.visibility = View.VISIBLE
                Log.e("Exercise", "COMPLETED ${model.id}")
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}