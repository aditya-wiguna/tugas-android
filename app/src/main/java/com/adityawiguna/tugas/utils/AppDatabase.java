package com.adityawiguna.tugas.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.adityawiguna.tugas.model.Mahasiswa;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MahasiswaDao mahasiswaDao();
}
