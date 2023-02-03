package dev.x001.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.x001.workoutapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpToolBar()

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

}