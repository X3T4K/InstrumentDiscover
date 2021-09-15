package com.liceoCairoli.instrumentdiscover.data

import androidx.lifecycle.LiveData
import com.liceoCairoli.instrumentdiscover.data.Instrument
import com.liceoCairoli.instrumentdiscover.data.InstrumentDao

class InstrumentRepository(private val instrumentDao: InstrumentDao) {

    val readAllData: LiveData<List<Instrument>> = instrumentDao.readAllData()

    suspend fun addInstrument(instrument: Instrument){
        instrumentDao.addInstrument(instrument)
    }

    suspend fun deleteInstrument(instrument: Instrument){
        instrumentDao.deleteInstrument(instrument)
    }

}