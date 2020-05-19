package com.adityawiguna.tugas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.adityawiguna.tugas.model.Mahasiswa;
import com.adityawiguna.tugas.utils.AppDatabase;
import com.adityawiguna.tugas.utils.MahasiswaAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_insert)
    Button btnInsert;

    @BindView(R.id.rv_mahasiswa)
    RecyclerView rvMahasiswa;

    AppDatabase db;
    List<Mahasiswa> mahasiswaList = new ArrayList<>();
    MahasiswaAdapter mahasiswaAdapter;

    @OnClick(R.id.btn_insert)
    public void setBtnInsert(){
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra("mode", "insert");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }

        fetchDataMahasiswa();
        initRecyclerView();
        setAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDataMahasiswa();
        initRecyclerView();
        setAdapter();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void fetchDataMahasiswa() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();
        mahasiswaList = db.mahasiswaDao().getAll();
    }

    private void initRecyclerView() {
        rvMahasiswa.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMahasiswa.setLayoutManager(llm);
        mahasiswaAdapter = new MahasiswaAdapter(this,mahasiswaList);
    }

    private void setAdapter() {
        rvMahasiswa.setAdapter(mahasiswaAdapter);
    }

}
