package com.liceoCairoli.instrumentdiscover.dB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InstrumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInstrument (instrument: Instrument): Any?

    @Delete()
    fun deleteInstrument (instrument: Instrument)

    @Query("SELECT * FROM instrument_table")
    public fun observeAllInstrument(): LiveData<List<Instrument>>
}