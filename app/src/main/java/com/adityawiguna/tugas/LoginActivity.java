package com.adityawiguna.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.adityawiguna.tugas.model.Data;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    String username, password;

    @OnClick(R.id.btn_register)
    public void setBtnRegister(){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_login)
    public void setBtnLogin(){
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();

        if(username.equals("admin") && password.equals("admin")){
            Data data = new Data("admin", "admin");
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("DATA", data);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }
}
