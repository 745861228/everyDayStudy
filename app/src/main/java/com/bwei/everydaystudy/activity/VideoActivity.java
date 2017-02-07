package com.bwei.everydaystudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwei.everydaystudy.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends AppCompatActivity {


    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        vv = (VideoView) findViewById(R.id.vv);
/*// 插件vitamio框架检查是否可用
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }

        vv.setVideoPath("http://video.jiecao.fm/8/16/%E9%B8%AD%E5%AD%90.mp4"); //设置播放路径
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
// 设置video的控制器
        vv.setMediaController(new MediaController(this));*/
        playfunction("http://video.jiecao.fm/8/16/%E9%B8%AD%E5%AD%90.mp4");

    }


    private void playfunction(String path) {

        if (path == "") {

            // Tell the user to provide a media file URL/path.
            return;

        } else {

/** Alternatively,for streaming media you can use

 * mVideoView.setVideoURI(Uri.parse(URLstring));*/

            vv.setVideoPath(path);

            vv.setMediaController(new MediaController(this));

            vv.requestFocus();

            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override

                public void onPrepared(MediaPlayer mediaPlayer) {

                    // optional need Vitamio 4.0

                    mediaPlayer.setPlaybackSpeed(1.0f);

                }

            });

        }

    }
}
