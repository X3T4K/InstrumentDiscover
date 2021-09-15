package com.liceoCairoli.instrumentdiscover.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.liceoCairoli.instrumentdiscover.data.Instrument

@Dao
interface InstrumentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInstrument(instrument: Instrument)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Instrument>>

    @Delete()
    suspend fun deleteInstrument (instrument: Instrument)



}