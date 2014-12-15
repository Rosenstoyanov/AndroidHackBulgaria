package com.example.rosen.helpludogorets;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

import java.io.File;


public class MyActivity extends Activity implements View.OnClickListener {

    private ImageButton btnPrev, btnPlayPause, btnNext;
    private VideoView mVideoViewer;
    private File videoFile;
    private boolean playPresed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        btnPrev = (ImageButton) findViewById(R.id.btnNext);
        btnPlayPause = (ImageButton) findViewById(R.id.btnPlayPause);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        mVideoViewer = (VideoView) findViewById(R.id.videoView);
        btnPrev.setOnClickListener(this);
        btnPlayPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        videoFile = new File(Environment.getExternalStorageDirectory(),"A.mp4");
        mVideoViewer.setVideoURI(Uri.fromFile(videoFile));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (btnPrev.getId() == R.id.btnPrev){
            if (mVideoViewer.canSeekBackward())
                mVideoViewer.seekTo(mVideoViewer.getCurrentPosition() - 3);
        }
        if (btnPlayPause.getId() == R.id.btnPlayPause){
            if (!playPresed){
                playPresed = true;
                btnPlayPause.setImageResource(R.drawable.pause);
                mVideoViewer.start();
            }
            else{
                playPresed = false;
                btnPlayPause.setImageResource(R.drawable.play);
                mVideoViewer.pause();

            }
            //File playFile = new File(R.drawable.play)
            //btnPlayPause.setImageURI();
//            mVideoViewer.setMediaController(new MediaController(this));
//            mVideoView.requestFocus();
//            mVideoView.start();
//            videoViewer.setVideoURI(Uri.fromFile(file));
//            videoMediaController = new MediaController(this);
//            mVideoView.setVideoPath(mUrl);
//            videoMediaController.setMediaPlayer(mVideoView);
//            mVideoView.setMediaController(videoMediaController);
//            mVideoView.requestFocus();
//            mVideoView.start();
        }
        if (btnNext.getId() == R.id.btnNext){

            if (mVideoViewer.canSeekForward())
                mVideoViewer.seekTo(mVideoViewer.getCurrentPosition() + 3);
            //mVideoViewer.next
        }
    }
}
