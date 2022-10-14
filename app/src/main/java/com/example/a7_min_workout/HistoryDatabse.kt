package com.example.a7_min_workout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HistoryEntity::class],version = 1, exportSchema = false)
abstract class HistoryDatabse : RoomDatabase() {

    abstract fun historydao():HistoryDao

    companion object{

        @Volatile
        var INSTANCE:HistoryDatabse?=null


        fun getinstance(context: Context):HistoryDatabse{

            synchronized(this){

                var instance= INSTANCE
                if(instance==null){
                    instance =  Room.databaseBuilder(context,HistoryDatabse::class.java,"workout_history").allowMainThreadQueries().build()

                    INSTANCE=instance
                }
                return instance


            }

        }
    }
}