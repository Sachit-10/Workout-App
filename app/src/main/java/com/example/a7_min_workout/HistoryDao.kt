package com.example.a7_min_workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert
    fun insertdate(historyEntity: HistoryEntity)

    @Query ("Select * from workout_history" )
    fun getalldates():LiveData<List<HistoryEntity>>
}