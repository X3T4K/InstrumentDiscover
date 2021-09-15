package com.liceoCairoli.instrumentdiscover.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InstrumentViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Instrument>>
    private val repository: InstrumentRepository

    init {
        val instrumentDao = InstrumentDatabase.getDatabase(application).instrumentDao()
        repository = InstrumentRepository(instrumentDao)
        readAllData = repository.readAllData
    }

    fun addInstrument(instrument: Instrument){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addInstrument(instrument)
        }
    }

    fun deleteInstrument(instrument: Instrument){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteInstrument(instrument)
        }
    }

}