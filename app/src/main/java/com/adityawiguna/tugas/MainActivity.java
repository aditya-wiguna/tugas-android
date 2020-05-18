package com.adityawiguna.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.adityawiguna.tugas.adapter.MahasiswaAdapter;
import com.adityawiguna.tugas.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_mahasiswa)
    ListView lvMahasiswa;

    private List<Mahasiswa> mahasiswaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        insertData();

        MahasiswaAdapter mahasiswaAdapter = new MahasiswaAdapter(this, R.layout.item_mahasiswa, mahasiswaList);
        lvMahasiswa.setAdapter(mahasiswaAdapter);
    }

    private void insertData(){
        mahasiswaList.add(new Mahasiswa("Test 1", "11", R.drawable.gambar1));
        mahasiswaList.add(new Mahasiswa("Test 2", "12", R.drawable.gambar2));
        mahasiswaList.add(new Mahasiswa("Test 3", "13", R.drawable.gambar3));
        mahasiswaList.add(new Mahasiswa("Test 4", "14", R.drawable.gambar4));
        mahasiswaList.add(new Mahasiswa("Test 5", "15", R.drawable.gambar5));
        mahasiswaList.add(new Mahasiswa("Test 6", "16", R.drawable.gambar6));
    }
}
