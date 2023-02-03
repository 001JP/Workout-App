package dev.x001.workoutapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import dev.x001.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var greeting = Constants.greetings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.greeting.text = greeting


        binding.startButton.setOnClickListener {

            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding.lottieProfile.setOnClickListener {
            binding.lottieProfile.playAnimation()
            binding.greeting.text = Constants.greetings()

        }

        binding.bmiCalculatorCardView.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        binding.historyCardView.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }


    }




    override fun onDestroy() {
        super.onDestroy()

        //binding = null
    }
}