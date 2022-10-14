package com.example.a7_min_workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7_min_workout.databinding.ActivityHistoryBinding

class history : AppCompatActivity(){
    var binding:ActivityHistoryBinding?=null
    val viewModel:HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        viewModel.getalldates().observe(this){datelist ->
            binding?.rvHistory?.layoutManager=LinearLayoutManager(this)


            val dates = ArrayList<String>()

            for(date in datelist){
                dates.add(date.date)
            }

            binding?.rvHistory?.adapter=HistoryAdapter(this,dates)
        }






    }
}