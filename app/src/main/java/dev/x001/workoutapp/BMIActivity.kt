package dev.x001.workoutapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import dev.x001.workoutapp.databinding.ActivityBmiactivityBinding

class BMIActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBmiactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpRadioGroup()
        calculateBtn()

    }

    private fun setUpRadioGroup(){
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.metric_radio_button -> {
                    binding.heightFeetEditText.visibility = View.INVISIBLE
                    binding.heightInchEditText.visibility = View.INVISIBLE
                    binding.weightPoundsEditText.visibility = View.INVISIBLE
                    binding.heightFeetLayout.visibility = View.INVISIBLE
                    binding.heightInchLayout.visibility = View.INVISIBLE
                    binding.weightPoundsLayout.visibility = View.INVISIBLE
                    binding.weightKgInputLayout.visibility = View.VISIBLE
                    binding.heightCmInputLayout.visibility = View.VISIBLE
                    binding.heightCmEditText.visibility = View.VISIBLE
                    binding.weightKgEditText.visibility = View.VISIBLE
                    binding.heightFeetEditText.text?.clear()
                    binding.heightInchEditText.text?.clear()
                    binding.weightPoundsEditText.text?.clear()
                    binding.heightCmEditText.error = null
                    binding.weightKgEditText.error = null
                }

                R.id.us_radio_button -> {
                    binding.heightFeetEditText.visibility = View.VISIBLE
                    binding.heightInchEditText.visibility = View.VISIBLE
                    binding.weightPoundsEditText.visibility = View.VISIBLE
                    binding.heightFeetLayout.visibility = View.VISIBLE
                    binding.heightInchLayout.visibility = View.VISIBLE
                    binding.weightPoundsLayout.visibility = View.VISIBLE
                    binding.weightKgInputLayout.visibility = View.INVISIBLE
                    binding.heightCmInputLayout.visibility = View.INVISIBLE
                    binding.heightCmEditText.visibility = View.INVISIBLE
                    binding.weightKgEditText.visibility = View.INVISIBLE
                    binding.heightCmEditText.text?.clear()
                    binding.weightKgEditText.text?.clear()
                    binding.heightFeetEditText.error = null
                    binding.heightInchEditText.error = null
                    binding.weightPoundsEditText.error = null
                }
            }

            if (binding.resultTextView.isVisible){
                binding.resultTextView.visibility = View.INVISIBLE
                binding.classificationTextView.visibility = View.INVISIBLE
            }

            closeNumberPad()

        }
    }

    private fun calculateBtn(){

        fun showResult(result: Double, classification: String){
            with(binding.resultTextView){
                visibility = View.VISIBLE
                text = "Your BMI is ${String.format("%.2f", result).toDouble()}"
            }

            with(binding.classificationTextView){
                visibility = View.VISIBLE
                text = "Classification: $classification"
            }
        }

        binding.calculateButton.setOnClickListener {

            //Calculation in metric
            if (binding.metricRadioButton.isChecked){
                if (binding.heightCmEditText.text!!.isNotEmpty() && binding.weightKgEditText.text!!.isNotEmpty()){
                    val height = binding.heightCmEditText.text.toString()
                    val weight = binding.weightKgEditText.text.toString()
                    val result = calculateBMI(height.toDouble(), weight.toDouble())
                    val classification = bmiClassification(result)

                    showResult(result, classification)

                }else{
                    if (binding.heightCmEditText.isVisible){
                        binding.heightCmEditText.error = "This field is required."
                        binding.weightKgEditText.error = "This field is required."
                    }
                }
            }

            //Calculation in us
            if (binding.usRadioButton.isChecked){
                if (binding.heightFeetEditText.text!!.isNotEmpty() &&
                    binding.heightInchEditText.text!!.isNotEmpty() &&
                    binding.weightPoundsEditText.text!!.isNotEmpty()) {

                    val feet = binding.heightFeetEditText.text.toString().toInt()
                    val inches = binding.heightInchEditText.text.toString().toInt()
                    val height = "$feet.$inches"
                    val weight = binding.weightPoundsEditText.text.toString().toDouble()

                    val heightConversion = height.toDouble()*30.48 //convert feet to cm
                    val weightConversion = weight/2.2 //convert pounds to kg

                    Log.e("RESULT", "$heightConversion, $weightConversion")

                    val result = calculateBMI(heightConversion, weightConversion)
                    val classification = bmiClassification(result)

                    showResult(result, classification)

                } else {
                    binding.heightFeetEditText.error = "This field is required."
                    binding.heightInchEditText.error = "This field is required."
                    binding.weightPoundsLayout.error = "This field is required."
                }
            }

            closeNumberPad()
        }
    }


    private fun closeNumberPad(){
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun calculateBMI(height: Double, weight: Double): Double{
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    private fun bmiClassification(result: Double): String{
        return when (result) {
            in 0.0..18.4 -> "Underweight"
            in 18.5..24.9 -> "Normal weight"
            in 25.0..29.9 -> "Overweight"
            else -> "Obesity"
        }
    }

    private fun setUpToolBar(){
        setSupportActionBar(binding.bmiToolbar)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.bmiToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }




}