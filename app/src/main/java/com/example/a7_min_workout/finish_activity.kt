package com.example.a7_min_workout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class finish_activity : AppCompatActivity() {

    val viewmodel:HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)


        var finish :Button = findViewById(R.id.finish_btn)

        finish.setOnClickListener(){


        val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
        val date: String = df.format(Calendar.getInstance().getTime())


        viewmodel.insertdate(HistoryEntity(date))

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}