package com.example.a7_min_workout

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7_min_workout.databinding.ActivityBmiBinding
import com.example.a7_min_workout.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class Bmi_activity : AppCompatActivity() {

    var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//         setSupportActionBar(binding?.toolbarBmi)
//
//        if(supportActionBar!=null){
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.title = "BMI Calculator"
//        }

//        binding?.toolbarBmi?.setNavigationOnClickListener(){
//            onBackPressed()
//        }

        binding?.btnCalculateUnits?.setOnClickListener() {


            if(validatemetricunits()) {

                val heightvalue: Float =
                    binding?.etHeight?.text.toString().toFloat() / 100    //converting cm into m
                val weightvalue: Float = binding?.etWeight?.text.toString().toFloat()

                val bmi =weightvalue/(heightvalue * heightvalue)

                displayBMIResult(bmi)


            }
            else if(validateUSunits()){
                val weight :Float = binding?.etUsWeight?.text?.toString()!!.toFloat()
                val feet :Float  =  binding?.etFeet?.text?.toString()!!.toFloat()
                val inches :Float  =  binding?.etInch?.text?.toString()!!.toFloat()
                val height :Float = (feet.toFloat()*12) + inches.toFloat()

                var bmi_us: Float = (weight/ (height*height) )* 703

                binding?.etUsWeight?.text.toString().toFloat()


                displayBMIResult(bmi_us)


            }
            else {
                Toast.makeText(this, "Please enter the valid details", Toast.LENGTH_SHORT).show()
            }
        }


        binding?.rbMetricUnits?.setOnClickListener() {
            binding?.llMetricunits?.visibility = View.VISIBLE
            binding?.llUsunits?.visibility = View.INVISIBLE
            binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
            binding?.etUsWeight?.text?.clear()
            binding?.etFeet?.text?.clear()
            binding?.etInch?.text?.clear()
        }

        binding?.rbUsUnits?.setOnClickListener() {
            binding?.llMetricunits?.visibility = View.INVISIBLE
            binding?.llUsunits?.visibility = View.VISIBLE
            binding?.llDiplayBMIResult?.visibility = View.INVISIBLE

            binding?.etWeight?.text?.clear()
            binding?.etHeight?.text?.clear()




        }

        //we can access radio buttons through setonclicklistiener but we can also use set on checkchanged listener ehich is as follow
        // Radio Group change listener is set to the radio group which is added in XML.
        //we use _ for the first value because we don't need it
//        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
//
//            // Here if the checkId is METRIC UNITS view then make the view visible else US UNITS view.
//            if (checkedId == R.id.rbMetricUnits) {
//                makeVisibleMetricUnitsView()
//            } else {
//                makeVisibleUsUnitsView()
//            }
//        }
        // END
        // Button will calculate the input values in Metric Units

    }

    fun validatemetricunits(): Boolean {                  //we are just cheking whether the user has writtrn something or not

        var isvalid = true

        if(binding?.etWeight?.text.toString().isEmpty()) {
            isvalid = false
        }

        if(binding?.etHeight?.text.toString().isEmpty()) {
            isvalid = false
        }

        return isvalid

    }

    fun validateUSunits(): Boolean {                  //we are just cheking whether the user has writtrn something or not

        var isvalid = true

        if(binding?.etUsWeight?.text.toString().isEmpty()) {
            isvalid = false
        }

        if(binding?.etFeet?.text.toString().isEmpty()) {
            isvalid = false
        }

        if(binding?.etInch?.text.toString().isEmpty()) {
            isvalid = false
        }


        return isvalid

    }


    private fun displayBMIResult(bmi: Float) {

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {                         // it means if bmi <= 15 but we can also write it as bmi<=15
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        //Use to set the result layout visible
        binding?.llDiplayBMIResult?.visibility = View.VISIBLE

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()   // so that only 2 no.s are visible after decimal point

        binding?.tvBMIValue?.text = bmiValue // Value is set to TextView
        binding?.tvBMIType?.text = bmiLabel // Label is set to TextView
        binding?.tvBMIDescription?.text = bmiDescription // Description is set to TextView
    }



}