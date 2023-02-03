package dev.x001.workoutapp

import android.animation.Animator
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.x001.workoutapp.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

        class ViewHolder(binding: ItemExerciseStatusBinding):
            RecyclerView.ViewHolder(binding.root){
            val itemFrameLayout = binding.itemFrameLayout
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
            model.isSelected -> {
                holder.itemFrameLayout.background =
                    ContextCompat.getDrawable(holder.itemTextView.context,
                    R.drawable.item_circular_white_bg)

                holder.itemTextView.setTextColor(Color.parseColor("#252831"))
                Log.e("Exercise", "SELECTED ${model.id}")
            }
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