package com.example.a7_min_workout

import androidx.lifecycle.LiveData

class HistoryRepository(val dao:HistoryDao) {

    fun getalldates():LiveData<List<HistoryEntity>>{
        return dao.getalldates()
    }

    fun insertdate(historyEntity: HistoryEntity){
        return dao.insertdate(historyEntity)
    }
}