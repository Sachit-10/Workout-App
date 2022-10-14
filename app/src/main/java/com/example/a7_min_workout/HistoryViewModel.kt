package com.example.a7_min_workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.lang.Appendable

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    val repository:HistoryRepository

    init {
        val dao = HistoryDatabse.getinstance(application).historydao()
        repository= HistoryRepository(dao)
    }

    fun insertdate(historyEntity: HistoryEntity){
        repository.insertdate(historyEntity)
    }

    fun getalldates():LiveData<List<HistoryEntity>> = repository.getalldates()
}