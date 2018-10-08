package chuangyuan.ycj.yjplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.exoplayer2.ExoPlaybackException;


import chuangyuan.ycj.videolibrary.listener.VideoInfoListener;
import chuangyuan.ycj.videolibrary.video.ExoUserPlayer;
import chuangyuan.ycj.videolibrary.video.ManualPlayer;
import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;
import chuangyuan.ycj.yjplay.R;

/**
 * Created by yangc on 2017/7/21.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */


public class BRVAHTestAdapter extends BaseQuickAdapter<String, BRVAHTestAdapter.TestVideoHolder> {
    private Context context;

    public BRVAHTestAdapter(Context context) {
        super(R.layout.item_video1);
        this.context = context;
    }

    @Override
    protected void convert(final TestVideoHolder helper, String item) {

        helper.userPlayer.setPlayUri(item);
        helper.userPlayer.setTag(helper.getAdapterPosition());
        //设置列表item播放当前进度一定设置.不然不会保存进度
        helper.userPlayer.setTag(helper.getAdapterPosition());
        //使用自定义预览布局
       helper.setText(R.id.exo_controls_title2, helper.getAdapterPosition()+"自定义预览标题");
        helper.setText(R.id.exo_controls_date, helper.getAdapterPosition()+"1:00");
        //如果使用自定义预览的布局，播放器标题根据业务是否设置
        helper.playerView.setTitle("自定义预览标题" + helper.getAdapterPosition());
        helper.playerView.setWGh(true);
        Glide.with(context)
                .load(context.getString(R.string.uri_test_image))
                .placeholder(R.mipmap.test)
                .into(helper.playerView.getPreviewImage());
        helper.userPlayer.addVideoInfoListener(new VideoInfoListener() {
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
            /*    helper.userPlayer.setVideoInfoListener(null);
                if (!VideoPlayUtils.isLand(getRecyclerView().getContext())) {
                    int posss = helper.getAdapterPosition() + 1;
                    getRecyclerView().smoothScrollBy(0, helper.itemView.getBottom());
                    View view = getRecyclerView().getLayoutManager().findViewByPosition(posss);
                    TestVideoHolder testVideoHolder = (TestVideoHolder) getRecyclerView().getChildViewHolder(view);
                    testVideoHolder.userPlayer.startPlayer();
                }*/

            }


            @Override
            public void isPlaying(boolean playWhenReady) {

            }
        });

    }

    @Override
    protected TestVideoHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return super.createBaseViewHolder(parent, layoutResId);
    }

    class TestVideoHolder extends BaseViewHolder {
        ManualPlayer userPlayer;
        VideoPlayerView playerView;
        View itemView;

        public TestVideoHolder(View view) {
            super(view);
            playerView = view.findViewById(R.id.exo_play_context_id);
            itemView = view;
            userPlayer = new VideoPlayerManager.Builder(VideoPlayerManager.TYPE_PLAY_MANUAL,playerView).create();
        }


    }

}
