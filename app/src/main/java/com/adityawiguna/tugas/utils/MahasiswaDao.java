package com.adityawiguna.tugas.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.adityawiguna.tugas.model.Mahasiswa;

import java.util.List;

@Dao
public interface MahasiswaDao {

    @Query("SELECT * FROM mahasiswa")
    List<Mahasiswa> getAll();

    @Insert
    void insertAll(Mahasiswa... mahasiswa);

    @Query("SELECT * FROM mahasiswa WHERE nim LIKE :nim ")
    Mahasiswa findByNim(String nim);


    @Delete
    public void deleteMahasiswa(Mahasiswa... mahasiswas);

    @Update
    public void update(Mahasiswa mahasiswa);

}
