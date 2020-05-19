package com.adityawiguna.tugas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaptureImageActivity extends AppCompatActivity {

    @BindView(R.id.imv_photo)
    ImageView imvPhoto;

    @BindView(R.id.btn_capture_image)
    Button btnCaptureImage;

    final int CAMERA_RESULT = 1;
    Uri uri = null;
    String pathImg = null, nameImg = null, dirImg = null;

    @OnClick(R.id.btn_capture_image)
    public void setBtnCaptureImage(){
        dirImg = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();

        File folder = new File(dirImg+File.separator+"AWIGUNA");
        boolean success = true;

        if(!folder.exists()){
            try {
                success = folder.mkdir();

                Toast.makeText(this, "Folder Berhasil Dibuat", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                success = !success;
                e.printStackTrace();
            }
        }

        if (success){
            dirImg = dirImg+File.separator+"AWIGUNA";
        }

        Calendar calendar = Calendar.getInstance();

        nameImg = "AWIGUNA"+calendar.getTimeInMillis()+".jpg";
        File file = new File(dirImg, nameImg);

        pathImg = dirImg+File.separator+nameImg;

        if(!file.exists()){
            try {
                file.createNewFile();

                uri = FileProvider.getUriForFile(CaptureImageActivity.this, BuildConfig.APPLICATION_ID, file);

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, CAMERA_RESULT);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                file.delete();
                file.createNewFile();

                uri = FileProvider.getUriForFile(CaptureImageActivity.this, BuildConfig.APPLICATION_ID, file);

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, CAMERA_RESULT);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_RESULT){
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);

                bitmap = automaticRotateImage(bitmap);
                imvPhoto.setImageBitmap(bitmap);
                replaceImageToFixImage(bitmap);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected Bitmap automaticRotateImage(Bitmap img){
        Bitmap bitmap = null;

        try {
            ExifInterface exifInterface = new ExifInterface(pathImg);

            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bitmap = rotateBitmap(img, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bitmap = rotateBitmap(img, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    bitmap = rotateBitmap(img, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                    bitmap = img;
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

    protected Bitmap rotateBitmap(Bitmap imgSource, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(imgSource, 0, 0, imgSource.getWidth(), imgSource.getHeight(), matrix, true);
    }

    private void replaceImageToFixImage(Bitmap img){
        File file = new File(dirImg, nameImg);

        if(file.exists()){
            file.delete();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                img.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e){

            }
        }
    }
}
