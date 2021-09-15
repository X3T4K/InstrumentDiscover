package com.liceoCairoli.instrumentdiscover.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Instrument::class], version = 1, exportSchema = false)
abstract class InstrumentDatabase : RoomDatabase() {

    abstract fun instrumentDao(): InstrumentDao

    companion object {
        @Volatile
        private var INSTANCE: InstrumentDatabase? = null

        fun getDatabase(context: Context): InstrumentDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InstrumentDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}