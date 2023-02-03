package dev.x001.workoutapp

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.x001.workoutapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFinishBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpToolBar()

        binding.trophyLottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                binding.confettiLottie.visibility = View.VISIBLE
                binding.confettiLottie.playAnimation()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })

        binding.trophyLottie.setOnClickListener{
            binding.confettiLottie.playAnimation()

        }

        binding.finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpToolBar(){
        setSupportActionBar(binding.toolbarFinish)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbarFinish.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }



}