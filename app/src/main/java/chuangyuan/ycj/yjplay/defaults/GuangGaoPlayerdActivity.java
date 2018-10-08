package chuangyuan.ycj.yjplay.defaults;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlaybackException;

import chuangyuan.ycj.videolibrary.listener.VideoInfoListener;
import chuangyuan.ycj.videolibrary.listener.VideoWindowListener;
import chuangyuan.ycj.videolibrary.video.ExoUserPlayer;
import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;
import chuangyuan.ycj.yjplay.R;

public class GuangGaoPlayerdActivity extends Activity {

    private ExoUserPlayer exoPlayerManager;
    private VideoPlayerView videoPlayerView;
    private static final String TAG = "OfficeDetailedActivity";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coutom2);
        videoPlayerView = findViewById(R.id.exo_play_context_id);
        //开启实时进度
        videoPlayerView.setOpenProgress2(true);
        exoPlayerManager = new VideoPlayerManager
                .Builder(VideoPlayerManager.TYPE_PLAY_USER, videoPlayerView)
                //设置视频标题
                .setTitle("视频标题")
                //设置水印图
                .setExoPlayWatermarkImg(R.mipmap.watermark_big)
                .setPlayUri(0, getString(R.string.uri_test_11), getString(R.string.uri_test_12))
                ///默认实现  播放广告视频时手势操作禁用和开启操作
                //exoPlayerManager.setPlayerGestureOnTouch(true);
                //如果视频需要自己实现该回调 视频切换回调处理，进行布局处理，控制布局显示
                .addOnWindowListener(new VideoWindowListener() {
                    @Override
                    public void onCurrentIndex(int currentIndex, int windowCount) {
                        if (currentIndex == 0) {
                            Log.d(TAG, "setOnWindowListener:" + currentIndex);
                            exoPlayerManager.next();
                            //屏蔽控制布局Shielding control layout
                            exoPlayerManager.hideControllerView(true);
                            //true如果屏蔽控制布局 但是需要显示全屏按钮。手动显示。
                        } else {
                            //恢复控制布局Recovery control layout
                            exoPlayerManager.showControllerView(true);
                        }
                    }
                }).addVideoInfoListener(new VideoInfoListener() {

                    @Override
                    public void onPlayStart(long currPosition) {

                    }

                    @Override
                    public void onLoadingChanged() {

                    }

                    @Override
                    public void onPlayerError(ExoPlaybackException e) {

                    }

                    @Override
                    public void onPlayEnd() {
                        Toast.makeText(getApplication(), "asd", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void isPlaying(boolean playWhenReady) {

                    }
                })
                .create()
                .startPlayer();


        Glide.with(this)
                .load(getString(R.string.uri_test_image))
                .fitCenter()
                .placeholder(R.mipmap.test)
                .into(videoPlayerView.getPreviewImage());
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayerManager.next();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
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
        if (exoPlayerManager.onBackPressed()) {
            ActivityCompat.finishAfterTransition(this);

        }
    }

}
