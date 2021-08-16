package com.liceoCairoli.instrumentdiscover.dB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Instrument.class}, version = 1, exportSchema = false)
public abstract class InstrumentRoomDatabase extends RoomDatabase {

    public abstract InstrumentDao instrumentDao();

    private static volatile InstrumentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static InstrumentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InstrumentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InstrumentRoomDatabase.class, "instrument_table")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
