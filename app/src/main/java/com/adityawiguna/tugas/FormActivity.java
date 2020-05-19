package com.adityawiguna.tugas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.adityawiguna.tugas.model.Mahasiswa;
import com.adityawiguna.tugas.utils.AppDatabase;
import com.adityawiguna.tugas.utils.ImageProcess;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText edtName;

    @BindView(R.id.edt_nim)
    EditText edtNim;

    @BindView(R.id.edt_umur)
    EditText edtUmur;

    @BindView(R.id.btn_insert)
    Button btnInsert;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    @BindView(R.id.btn_delete)
    Button btnDelete;

    @BindView(R.id.btn_view)
    Button btnView;

    @BindView(R.id.imv_mahasiswa)
    ImageView imvMahasiswa;

    Mahasiswa mahasiswa;
    AppDatabase db;

    public static final int PICK_FILE_RESULT_CODE = 100;
    String path = null;
    String mode = "insert", nim = "";

    @OnClick(R.id.btn_insert)
    public void setBtnInsert(){
        if (!edtName.getText().toString().isEmpty() && !edtNim.getText().toString().isEmpty() && !edtUmur.getText().toString().isEmpty()){
            mahasiswa = new Mahasiswa();
            mahasiswa.setName(edtName.getText().toString());
            mahasiswa.setNim(edtNim.getText().toString());
            mahasiswa.setUmur(edtUmur.getText().toString());
            mahasiswa.setPath(path);

            db.mahasiswaDao().insertAll(mahasiswa);
            Log.d("DATA", db.mahasiswaDao().getAll().toString());
            Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Form dilengkapin dulu", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_view)
    public void setBtnView(){
        finish();
    }

    @OnClick(R.id.btn_update)
    public void setBtnUpdate(){
        if (!edtName.getText().toString().isEmpty() && !edtNim.getText().toString().isEmpty() && !edtUmur.getText().toString().isEmpty()){
            mahasiswa = new Mahasiswa();
            mahasiswa.setName(edtName.getText().toString());
            mahasiswa.setNim(edtNim.getText().toString());
            mahasiswa.setUmur(edtUmur.getText().toString());
            mahasiswa.setPath(path);

            db.mahasiswaDao().update(mahasiswa);
            Log.d("DATA", db.mahasiswaDao().getAll().toString());
            Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Form dilengkapin dulu", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_delete)
    public void setBtnDelete(){
        db.mahasiswaDao().deleteMahasiswa(mahasiswa);
        Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ButterKnife.bind(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();

        imvMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg|image/png");
                i = Intent.createChooser(i, "Pilih File : ");
                startActivityForResult(i, PICK_FILE_RESULT_CODE);
            }
        });

        Intent i = getIntent();
        mode = i.getStringExtra("mode");

        if(mode.equals("detail")){
            nim = i.getStringExtra("nim");

            mahasiswa = db.mahasiswaDao().findByNim(nim);

            edtName.setText(mahasiswa.getName());
            edtNim.setText(mahasiswa.getNim());
            edtUmur.setText(mahasiswa.getUmur());
            btnDelete.setEnabled(true);
            btnUpdate.setEnabled(true);
        } else {
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null){
            return;
        }

        switch (requestCode){
            case PICK_FILE_RESULT_CODE:
                Uri fileUri = data.getData();
                path = getRealPathFromUri(fileUri);

                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 0;
                    Bitmap mypic = BitmapFactory.decodeFile(path, options);
                    ImageProcess ip = new ImageProcess();
                    mypic = ip.automaticRotateImage(mypic, path);

                    imvMahasiswa.setImageBitmap(mypic);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    private String getRealPathFromUri(Uri uri){
        String result;

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null){
            result = uri.getPath();
        } else {
            cursor.moveToFirst();

            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }

        return result;
    }
}
