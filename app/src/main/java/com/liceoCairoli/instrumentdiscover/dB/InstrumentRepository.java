package com.liceoCairoli.instrumentdiscover.dB;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class InstrumentRepository {

    private InstrumentDao mWordDao;
    private LiveData<List<Instrument>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public  InstrumentRepository(Application application) {
        InstrumentRoomDatabase db = InstrumentRoomDatabase.getDatabase(application);
        mWordDao = db.instrumentDao();
        mAllWords = mWordDao.observeAllInstrument();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Instrument>> getAllInstrument() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
     public void insert(Instrument instrument) {
        InstrumentRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insertInstrument(instrument);
        });
    }
}
