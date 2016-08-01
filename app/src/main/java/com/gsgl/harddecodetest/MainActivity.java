package com.gsgl.harddecodetest;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    public String TAG = "MainActivity";
    public SurfaceView   mSurfaceView;
    public SurfaceHolder mSurfaceHolder;
    public boolean       mIsPlay = false;

    MediaCodecTools mMediaCodecTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view_player);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated Called!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMediaCodecTools = new MediaCodecTools();
                try {
                    mMediaCodecTools.setSaveFrames("/sdcard/decodeFrames", MediaCodecTools.FILE_DISPLAY);
                    mMediaCodecTools.videoDecode("/sdcard/test2.mp4", mSurfaceHolder);
                    //这个文件不行
                    //mMediaCodecTools.videoDecode("/sdcard/720pq.h264");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


}
