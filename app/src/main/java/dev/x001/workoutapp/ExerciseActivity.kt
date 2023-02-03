package dev.x001.workoutapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.x001.workoutapp.databinding.ActivityExerciseBinding
import dev.x001.workoutapp.databinding.DialogBackBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding : ActivityExerciseBinding

    private lateinit var countDownTimer: CountDownTimer
    private var timerDuration: Long = 0

    private lateinit var exerciseList: ArrayList<ExerciseModel>
    private var currentExercisePosition = -1 //-1
    private lateinit var currentExercise : ExerciseModel
    private lateinit var tts: TextToSpeech
    private lateinit var player: MediaPlayer

    private lateinit var exerciseAdapter : ExerciseStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()

        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()

        speak("Get ready.")

        restTimer()
        setToggleButton()
        setupExerciseStatusRecyclerView()
    }

    private fun setUpToolBar(){
        setSupportActionBar(binding.toolbarExercise)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setToggleButton(){
        binding.speakerToggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.speakerToggleButton.setBackgroundResource(R.drawable.ic_speaker_on)
            } else {
                binding.speakerToggleButton.setBackgroundResource(R.drawable.ic_speaker_off)
            }
        }
    }

    override fun onBackPressed() {
        backDialog()
    }

    private fun backDialog(){
        val dialog = Dialog(this)
        val dialogBinding : DialogBackBinding = DialogBackBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)

        dialogBinding.noButton.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.yesButton.setOnClickListener {
            this@ExerciseActivity.finish()
            dialog.dismiss()
        }

        dialog.show()
    }


    private fun setupExerciseStatusRecyclerView(){
        binding.exerciseStatusRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList)
        binding.exerciseStatusRecyclerView.adapter = exerciseAdapter
    }



    private fun restTimer(){
        timerDuration = 11000
        binding.restTimerView.visibility = View.VISIBLE
        binding.exerciseTimerView.visibility = View.GONE
        binding.lottieExercise.visibility = View.INVISIBLE
        binding.nextText.visibility = View.VISIBLE
        binding.nextExercise.visibility = View.VISIBLE
        binding.nextExercise.text = exerciseList[currentExercisePosition+1].name
        binding.lottieExerciseTimer.pauseAnimation()

        if(currentExercisePosition != -1){
            binding.exerciseName.text = "Take some rest"
        }


        countDownTimer = object  : CountDownTimer(timerDuration, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                if(millisUntilFinished.toInt()/1000 == 9 && currentExercisePosition == -1){
                    speak("Get ready.")
                }
                if(millisUntilFinished.toInt()/1000 == 5){
                    speak("Upcoming Exercise: ${binding.nextExercise.text}")
                }
                binding.textViewTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList[currentExercisePosition].isSelected = true
                exerciseAdapter.notifyDataSetChanged()
                exerciseTimer()

            }
        }.start()


    }

    private fun exerciseTimer(){
        currentExercise  = exerciseList[currentExercisePosition]

        timerDuration = 31000
        binding.restTimerView.visibility = View.INVISIBLE
        binding.exerciseTimerView.visibility = View.VISIBLE
        binding.lottieExercise.visibility = View.VISIBLE
        binding.nextText.visibility = View.INVISIBLE
        binding.nextExercise.visibility = View.INVISIBLE
        binding.lottieExerciseTimer.playAnimation()
        binding.exerciseName.text = currentExercise.name
        binding.lottieExercise.setAnimation(currentExercise.image)
        binding.lottieExercise.playAnimation()


        countDownTimer = object  : CountDownTimer(timerDuration, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                if(millisUntilFinished.toInt()/1000 == 0) playDoneSound()
                binding.textViewExerciseTimer.text = (millisUntilFinished / 1000).toString()

            }

            override fun onFinish() {
                exerciseList[currentExercisePosition].isSelected = false
                exerciseList[currentExercisePosition].isCompleted = true

                if(currentExercisePosition < exerciseList.size - 1){
                    restTimer()
                } else {
                    val intent = Intent(applicationContext, FinishActivity::class.java)
                    startActivity(intent)

                }
            }
        }.start()
    }

    private fun speak(message: String){
        if (binding.speakerToggleButton.isChecked){
            tts.speak(message, TextToSpeech.QUEUE_ADD, null, "")
        }
    }

    private fun playDoneSound(){
        if (binding.speakerToggleButton.isChecked){
            try {
                val soundURI = Uri.parse("android.resource://dev.x001.workoutapp/" + R.raw.ding_sound)
                player = MediaPlayer.create(applicationContext, soundURI)
                player.isLooping = false
                player.start()
            } catch (e: Exception){
                Log.e("Error", "Error playing media sound occurred")
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()

        countDownTimer.cancel()
        binding.textViewTimer.text = "10"
        if(tts.isSpeaking) tts.shutdown()



    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("Text-To-Speech", "Language not supported")
            }
        } else {
            Log.e("Text-To-Speech", "Init failed")
        }
    }


}