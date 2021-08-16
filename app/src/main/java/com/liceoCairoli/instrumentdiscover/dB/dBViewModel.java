package com.liceoCairoli.instrumentdiscover.dB;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class dBViewModel extends AndroidViewModel {

    private final InstrumentRepository mRepository;

    private final LiveData<List<Instrument>> mAllInstruments;

    public dBViewModel(Application application) {
        super(application);
        mRepository = new InstrumentRepository(application);
        mAllInstruments = mRepository.getAllInstrument();
    }

    LiveData<List<Instrument>> getAllWords() { return mAllInstruments; }

    public void insert(Instrument instrument) { mRepository.insert(instrument); }
}
