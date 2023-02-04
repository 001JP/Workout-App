package dev.x001.workoutapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.x001.workoutapp.R
import dev.x001.workoutapp.database.HistoryEntity
import dev.x001.workoutapp.databinding.ItemHistoryBinding

class HistoryAdapter(private val records: ArrayList<HistoryEntity>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.layout
        val dateAndTimeTextView = binding.dateAndTime
        val statusTextView = binding.status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history: HistoryEntity = records[position]
        holder.dateAndTimeTextView.text = history.dateAndTime
        holder.statusTextView.text = history.status

        if (position % 2 == 0){
            holder.layout.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                R.color.card_color))
        }
    }

    override fun getItemCount(): Int {
        return records.size
    }
}