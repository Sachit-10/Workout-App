package com.example.a7_min_workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import com.example.a7_min_workout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding :ActivityMainBinding?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.flstart?.setOnClickListener() {

            Toast.makeText(this, "Now you can start the exercise", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,excerciseactivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener() {

            val intent = Intent(this, Bmi_activity::class.java)
            startActivity(intent)
        }

        binding?.flHistory?.setOnClickListener() {

            val intent = Intent(this, history::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroy() {    //we creating this to make binding null to avoid memory leakage
        super.onDestroy()

        binding = null
    }


}