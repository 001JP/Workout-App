package dev.x001.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.x001.workoutapp.adapter.HistoryAdapter
import dev.x001.workoutapp.database.HistoryApp
import dev.x001.workoutapp.database.HistoryDAO
import dev.x001.workoutapp.database.HistoryEntity
import dev.x001.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpToolBar()

        val historyDAO = (application as HistoryApp).db.historyDao()

        lifecycleScope.launch {
            historyDAO.fetchAllRecords().collect{
                val records = ArrayList(it)
                setUpRecyclerView(records)
            }
        }

    }

    private fun setUpToolBar(){
        setSupportActionBar(binding.toolbar)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpRecyclerView(historyList: ArrayList<HistoryEntity>){

        if(historyList.isNotEmpty()){
            binding.noDataAvailableTextView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE

            val historyAdapter = HistoryAdapter(historyList)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = historyAdapter
        }
    }

}