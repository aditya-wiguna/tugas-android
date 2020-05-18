package com.adityawiguna.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_absensi)
    TextView tvAbsensi;

    @BindView(R.id.tv_quiz)
    TextView tvQuiz;

    @BindView(R.id.tv_tugas)
    TextView tvTugas;

    @BindView(R.id.tv_uts)
    TextView tvUTS;

    @BindView(R.id.tv_uas)
    TextView tvUAS;

    @BindView(R.id.tv_kesimpulan)
    TextView tvKesimpulan;

    @BindView(R.id.tv_total)
    TextView tvTotal;

    private String absensi, quiz, tugas, uts, uas, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Intent i = getIntent();
        absensi = "Absensi : "+i.getStringExtra("absensi")+" * 15% = "+calculate(15, Integer.parseInt(i.getStringExtra("absensi")));
        tugas = "Tugas : "+i.getStringExtra("tugas")+" * 15% = "+calculate(15, Integer.parseInt(i.getStringExtra("tugas")));
        quiz = "Quiz : "+i.getStringExtra("quiz")+" * 20% = "+calculate(20, Integer.parseInt(i.getStringExtra("quiz")));
        uts = "UTS : "+i.getStringExtra("uts")+" * 20% = "+calculate(20, Integer.parseInt(i.getStringExtra("uts")));
        uas = "UAS : "+i.getStringExtra("uas")+" * 30% = "+calculate(30, Integer.parseInt(i.getStringExtra("uas")));
        total = "Total : "+i.getStringExtra("total")+"";

        Log.d("TOTAL", String.valueOf(calculate(20, 100)));

        tvAbsensi.setText(absensi);
        tvTugas.setText(tugas);
        tvQuiz.setText(quiz);
        tvUTS.setText(uts);
        tvUAS.setText(uas);
        tvTotal.setText(total);
        tvKesimpulan.setText(i.getStringExtra("keterangan"));
    }

    private int calculate(int percentage, int value){
        return value * percentage / 100;
    }
}
