package com.adityawiguna.tugas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordVideoActivity extends AppCompatActivity {

    @BindView(R.id.btn_record_video)
    Button btnRecordVideo;

    @BindView(R.id.vv_video)
    VideoView vvVideo;

    private int VIDEO_RESULT = 1;
    private Uri uri;
    private MediaController mediaController;

    @OnClick(R.id.btn_record_video)
    public void setBtnRecordVideo(){
        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();

        File folder = new File(dir+File.separator+"VIDEOAW");
        Boolean success = true;

        if(!folder.exists()){
            try {
                success = folder.mkdir();
                Toast.makeText(this, "Folder Berhasil Dibuat", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                success = !success;
                e.printStackTrace();
            }
        }

        if(success){
            dir = dir+File.separator+"VIDEOAW";
        }

        Calendar calendar = Calendar.getInstance();

        File file = new File(dir, "AWIGUNA"+calendar.getTimeInMillis()+".mp4");

        if(!file.exists()){
            try {
                file.createNewFile();

                uri = FileProvider.getUriForFile(RecordVideoActivity.this, BuildConfig.APPLICATION_ID, file);

                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, VIDEO_RESULT);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                file.delete();
                file.createNewFile();

                uri = FileProvider.getUriForFile(RecordVideoActivity.this, BuildConfig.APPLICATION_ID, file);

                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, VIDEO_RESULT);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);

        ButterKnife.bind(this);

        mediaController = new MediaController(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == VIDEO_RESULT){
            if(resultCode == RESULT_OK){
                Uri vidUri = data.getData();
                vvVideo.setMediaController(mediaController);
                vvVideo.setVideoURI(vidUri);
                vvVideo.start();
            }
        }
    }
}
