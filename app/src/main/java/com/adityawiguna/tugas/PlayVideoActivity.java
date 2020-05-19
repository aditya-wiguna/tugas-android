package com.adityawiguna.tugas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayVideoActivity extends AppCompatActivity {

    @BindView(R.id.btn_select_file)
    Button btnSelectFile;

    @BindView(R.id.vv_video)
    VideoView vvVideo;

    private final int PICKFILE_RESULT_CODE = 1;
    private MediaController mediaController;

    @OnClick(R.id.btn_select_file)
    public void setBtnSelectFile(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/mp4");

        try {
            startActivityForResult(i, PICKFILE_RESULT_CODE);
        } catch (ActivityNotFoundException e){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        ButterKnife.bind(this);
        mediaController = new MediaController(this);
        vvVideo.setMediaController(mediaController);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null){
            return;
        }

        switch (requestCode){
            case PICKFILE_RESULT_CODE:
                try {
                    String filePath = data.getData().getPath();
                    Uri uriVideo = Uri.parse(filePath);
                    vvVideo.setVideoURI(uriVideo);
                    Log.d("URI VIDEO", uriVideo.toString());
                    vvVideo.start();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
