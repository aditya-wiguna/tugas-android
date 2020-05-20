package com.adityawiguna.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adityawiguna.tugas.model.Data;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.btn_register)
    Button btnRegister;

    String username, password;

    @OnClick(R.id.btn_register)
    public void setBtnRegister(){
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();

        if(!username.equals("") && !password.equals("")){
            Data data = new Data(username, password);
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("DATA", data);
            startActivity(i);
        } else {
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }
}
