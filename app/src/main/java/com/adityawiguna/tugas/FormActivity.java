package com.adityawiguna.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText edtName;

    @BindView(R.id.edt_absensi)
    EditText edtAbsensi;

    @BindView(R.id.edt_quiz)
    EditText edtQuiz;

    @BindView(R.id.edt_tugas)
    EditText edtTugas;

    @BindView(R.id.edt_uts)
    EditText edtUTS;

    @BindView(R.id.edt_uas)
    EditText edtUAS;

    @BindView(R.id.btn_delete)
    Button btnDelete;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private int totalNilai;
    private String keterangan;

    @OnClick(R.id.btn_delete)
    public void delete(){
        edtName.setText("");
        edtAbsensi.setText("0");
        edtQuiz.setText("0");
        edtTugas.setText("0");
        edtUTS.setText("0");
        edtUAS.setText("0");
    }

    @OnClick(R.id.btn_submit)
    public void submit(){
        totalNilai = calculate(15, Integer.parseInt(edtAbsensi.getText().toString()))
                + calculate(15, Integer.parseInt(edtQuiz.getText().toString()))
                + calculate(20, Integer.parseInt(edtTugas.getText().toString()))
                + calculate(20, Integer.parseInt(edtUTS.getText().toString()))
                + calculate(30, Integer.parseInt(edtUAS.getText().toString()));

        if (totalNilai > 58){
            keterangan = "Lulus";
        } else {
            keterangan = "Tidak Lulus";
        }

        Intent i = new Intent(FormActivity.this, MainActivity.class);
        i.putExtra("name", edtName.getText().toString());
        i.putExtra("absensi", edtAbsensi.getText().toString());
        i.putExtra("quiz", edtQuiz.getText().toString());
        i.putExtra("tugas", edtTugas.getText().toString());
        i.putExtra("uts", edtUTS.getText().toString());
        i.putExtra("uas", edtUAS.getText().toString());
        i.putExtra("total", String.valueOf(totalNilai));
        i.putExtra("keterangan", keterangan);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ButterKnife.bind(this);
    }

    private int calculate(int percentage, int value){
        return value * percentage / 100;
    }
}
