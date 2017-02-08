package com.talon.videoviewdemo;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextureVideoView mVideoView;

    private ProgressBar mProgressBar;
    private ProgressBar mTextProgressBar;
    private TextView mTextView;
    private TextView mTextView2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mVideoView != null) {
                mTextProgressBar.setProgress(msg.arg1 * 100 / mVideoView.getDuration());
                mTextView.setText(msg.arg1 + "   :   " + mVideoView.getDuration());
                Message msg1 = mHandler.obtainMessage();
                msg1.arg1 = mVideoView.getCurrentPosition();
                mHandler.sendMessageDelayed(msg1, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = (TextureVideoView) findViewById(R.id.videoView_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_main);
        mTextProgressBar = (ProgressBar) findViewById(R.id.textProgressBar_main);
        mTextView = (TextView) findViewById(R.id.textView_main);
        mTextView2 = (TextView) findViewById(R.id.textView2_main);
        initView();

        /*VideoView vv = new VideoView(this);
        vv.setMediaController(new MediaController(this));
        vv.getBufferPercentage();*/
    }

    private void initView() {
        mProgressBar.setVisibility(View.VISIBLE);
        String path = "https://media.linknow.top/b6452fac1be24b5f91fa4c3808c67461.mp4";
//        String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//        String path = "http://flv.bn.netease.com/videolib3/1601/13/RzAQP8148/HD/RzAQP8148-mobile.mp4";
//        String path = "http://www.modrails.com/videos/passenger_nginx.mov";
        HttpProxyCacheServer proxy = MyApplication.getInstance().getProxy();
        String proxyUrl = proxy.getProxyUrl(path);
        Log.i(TAG, "initView: " + proxyUrl);
        try {
//        mVideoView.setVideoPath(proxyUrl);
            mVideoView.setDataSource(proxyUrl);
            mVideoView.requestFocus();
//            mVideoView.setMediaController(new MediaController(this));
            mVideoView.prepareAsync(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mp.start();
                    mp.setLooping(true);

                    Message msg = mHandler.obtainMessage();
                    msg.arg1 = mVideoView.getCurrentPosition();
                    mHandler.sendMessage(msg);

                    mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                        @Override
                        public void onBufferingUpdate(MediaPlayer mp, int percent) {
                            mTextView2.setText("缓存进度：" + percent);
                        }
                    });
                   /* mVideoView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "" + mp.getCurrentPosition(), Toast.LENGTH_SHORT).show();
                        }
                    }, 500);*/
                }
            });
           /* mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mp.start();
                    mp.setLooping(true);
                }
            });*/
//            mVideoView.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null)
            mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null)
            mVideoView.stop();
        mHandler.removeCallbacksAndMessages(null); // 很关键，不然退出后会报空指针异常
    }
}
