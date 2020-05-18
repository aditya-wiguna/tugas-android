package com.adityawiguna.tugas.model;

public class Mahasiswa {
    private String nama;
    private String nim;
    private int foto;

    public Mahasiswa(String nama, String nim, int foto) {
        this.nama = nama;
        this.nim = nim;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
