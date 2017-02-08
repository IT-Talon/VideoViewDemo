package com.talon.videoviewdemo;

import android.content.Context;
import android.util.AttributeSet;

import com.yqritc.scalablevideoview.ScalableVideoView;

/**
 * Created by 003 on 2017-01-10.
 */
public class TextureVideoView extends ScalableVideoView {
    public TextureVideoView(Context context) {
        super(context);
    }

    public TextureVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextureVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isMediaPlayerInited() {
        return mMediaPlayer != null;
    }

    @Override
    public void start() {
        if (mMediaPlayer != null) {
            super.start();
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null) {
            super.pause();
        }
    }

    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            super.stop();
        }
    }

    @Override
    public void release() {
        if (mMediaPlayer != null) {
            super.release();
        }
    }

    @Override
    public void reset() {
        if (mMediaPlayer != null) {
            super.reset();
        }
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

}