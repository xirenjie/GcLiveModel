package com.guocai.gclive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.guocai.gclive.R;
import com.guocai.gclive.utils.LogUtils;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

/**
 * Create  By xrj ON 2018/9/14 0014
 */
public class VideoFragment extends BaseFragment{
    PLVideoTextureView vtv;
    private String url;
    private int mRotation;
    private boolean isFull;



    @Override
    public View initLayout(LayoutInflater inflater) {
        mView = View.inflate(getContext(), R.layout.video_layout, null);
        //取消状态栏
        return mView;
    }


    public static VideoFragment newInstance(String url,boolean isFull) {

        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.url = url;
        fragment.isFull = isFull;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        vtv=mView.findViewById(R.id.vtv);
        LogUtils.d("url:" + url);
        vtv.setVideoPath(url);
        if(isFull){
            vtv.setDisplayOrientation(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        }else{
            vtv.setDisplayOrientation(PLVideoView.ASPECT_RATIO_16_9);
        }



        vtv.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer) {
                LogUtils.d("onPrepared:" + url);
                start();
            }
        });
        vtv.setOnBufferingUpdateListener(new PLMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
                if(i>0)
                    LogUtils.d("onBufferingUpdate|" + i);
            }
        });
        vtv.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                LogUtils.d("onCompletion");
            }
        });
        vtv.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
                LogUtils.d("onInfo|i:" + i + "--i1:" + i1);
                return false;
            }
        });

        vtv.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                LogUtils.w("onError:i:" + i);

                return false;
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickEvent(View v) {

    }

    public PLVideoTextureView getVideoView(){
        return vtv;
    }

    public boolean isPlaying(){
        return vtv.isPlaying();
    }

    private void start(){
        if(vtv!=null)
            vtv.start();
    }
    public void pause(){
        if(vtv!=null)
            vtv.pause();
    }

    public void stopPlayback(){
        if(vtv!=null)
            vtv.stopPlayback();

    }

    public void seekTo(long i){
        vtv.seekTo(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    public int getDisplayAspectRatio(){
        return vtv.getDisplayAspectRatio();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayback();
    }


    public void onClickRotate(View v) {
        mRotation = (vtv.getDisplayAspectRatio() + 90) % 360;
        setDisplayAspectRatio(mRotation);
    }

    /**
     *
     * @param ratio
     *      PLVideoView.ASPECT_RATIO_ORIGIN
     *      PLVideoView.ASPECT_RATIO_FIT_PARENT
     *      PLVideoView.ASPECT_RATIO_PAVED_PARENT
     *      PLVideoView.ASPECT_RATIO_16_9
     *      PLVideoView.ASPECT_RATIO_4_3
     *
     */
    public void setDisplayAspectRatio(int ratio){
        vtv.setDisplayAspectRatio(ratio);
    }

    /**
     *
     * @param orientation
     *      0/90/180/270
     */
    public void setDisplayOrientation(int orientation){
        vtv.setDisplayOrientation(orientation);
    }

    public void play(String url){
        this.url = url;
        LogUtils.d("url:" + url);
        vtv.setVideoPath(url);
        vtv.start();
    }





}
