package com.example.a7_min_workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7_min_workout.databinding.HistoryitemrvBinding

class HistoryAdapter(val context: Context,val historylist: ArrayList<String>): RecyclerView.Adapter<HistoryAdapter.viewholder>() {

    class viewholder(val binding:HistoryitemrvBinding) :RecyclerView.ViewHolder(binding.root) {

        var sno = binding.sno
        var tvdate =binding.workoutdate


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       return viewholder(HistoryitemrvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val date = historylist[position]

         holder.sno.text=(position+1).toString()
        holder.tvdate.text= date.toString()

    }

    override fun getItemCount(): Int {
        return historylist.size
    }


}