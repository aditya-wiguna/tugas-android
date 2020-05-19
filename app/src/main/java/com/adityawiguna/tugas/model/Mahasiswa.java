package com.adityawiguna.tugas.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mahasiswa {
    @ColumnInfo(name = "name")
    private String name;

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "nim")
    private String nim;

    @ColumnInfo(name = "path")
    private String path;

    @ColumnInfo(name = "umur")
    private String umur;

//    public Mahasiswa(String name, String nim, String path, String umur) {
//        this.name = name;
//        this.nim = nim;
//        this.path = path;
//        this.umur = umur;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }
}
