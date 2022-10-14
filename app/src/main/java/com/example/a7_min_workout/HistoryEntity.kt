package com.example.a7_min_workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.intellij.lang.annotations.PrintFormat

@Entity(tableName = "workout_history")
data class HistoryEntity(

    @PrimaryKey
    val date:String
)
