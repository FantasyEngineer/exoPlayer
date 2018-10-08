package chuangyuan.ycj.yjplay.media;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;

import chuangyuan.ycj.videolibrary.listener.VideoInfoListener;
import chuangyuan.ycj.videolibrary.listener.VideoWindowListener;
import chuangyuan.ycj.videolibrary.video.ExoUserPlayer;
import chuangyuan.ycj.videolibrary.video.ManualPlayer;
import chuangyuan.ycj.videolibrary.video.MediaSourceBuilder;
import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;
import chuangyuan.ycj.yjplay.R;
import chuangyuan.ycj.yjplay.data.DataSource;


public class MainCustomMediaActivity extends AppCompatActivity {

    private ManualPlayer exoPlayerManager;
    private VideoPlayerView videoPlayerView;
    private static final String TAG = "MainCustomMediaActivity";
    private String url = "";
    MediaSourceBuilder mediaSourceBuilder;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coutom);
        url = getIntent().getStringExtra("uri");
        videoPlayerView = findViewById(R.id.exo_play_context_id);
        Glide.with(this)
                .load(getString(R.string.uri_test_image))
                .placeholder(R.mipmap.test)
                .fitCenter()
                .into(videoPlayerView.getPreviewImage());
        mediaSourceBuilder = new MediaSourceBuilder(this, new DataSource(getApplication()));
        videoPlayerView.setTitle("自定义视频标题");
        //设置加载显示模式
        //  mediaSourceBuilder.setClippingMediaUri(Uri.parse(getString(R.string.uri_test_6)),1000,15000);
        MediaSource source = mediaSourceBuilder.initMediaSource(Uri.parse(getString(R.string.uri_test)));
        LoopingMediaSource loopingSource = new LoopingMediaSource(source, 2);
        mediaSourceBuilder.setMediaSource(loopingSource);


        exoPlayerManager = new VideoPlayerManager
                .Builder(VideoPlayerManager.TYPE_PLAY_MANUAL, videoPlayerView)
                .setDataSource(mediaSourceBuilder)
                .addOnWindowListener(new VideoWindowListener() {
                    @Override
                    public void onCurrentIndex(int currentIndex, int windowCount) {
                        Log.d(TAG, "currentIndex:" + currentIndex + "_windowCount:" + currentIndex);
                    }
                })
                .create()
                .startPlayer();


        //自定义布局使用
        videoPlayerView.getReplayLayout().findViewById(R.id.replay_btn_imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainCustomMediaActivity.this, "自定义分享", Toast.LENGTH_SHORT).show();
            }
        });
        videoPlayerView.getErrorLayout().findViewById(R.id.exo_player_error_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainCustomMediaActivity.this, "自定义错误", Toast.LENGTH_SHORT).show();
            }
        });
        videoPlayerView.getPlayHintLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainCustomMediaActivity.this, "自定义提示", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        exoPlayerManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        exoPlayerManager.onPause();
    }

    @Override
    protected void onDestroy() {
        exoPlayerManager.onDestroy();
        super.onDestroy();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        exoPlayerManager.onConfigurationChanged(newConfig);//横竖屏切换
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (exoPlayerManager.onBackPressed()) {//使用播放返回键监听
            Toast.makeText(MainCustomMediaActivity.this, "返回", Toast.LENGTH_LONG).show();
            finish();
        }

    }


}
