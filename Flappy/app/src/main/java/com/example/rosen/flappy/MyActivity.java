package com.example.rosen.flappy;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyActivity extends Activity implements LogInLicener {
//        implements GameClock.GameClockListener, View.OnClickListener{
//    MediaPlayer mediaPlayer;
//    DrawingView drawingView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //interface logIn licenar method onLogIn
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_holder_layout);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
//        LogInFragment logInFragment = new LogInFragment(this);
//        fragmentTransaction.add(R.id.linearLayout, logInFragment);
        GameFragment gameFragment = new GameFragment(this);
        fragmentTransaction.add(R.id.linearLayout, gameFragment);
        fragmentTransaction.commit();

//        FragmentManager fragmentManager = new LogInFragment();
//        fragmentManager.beginTransaction();
//        fragmentManager.
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.prey_overture);
//        mediaPlayer.setLooping(true);
//        drawingView = (DrawingView) findViewById(R.id.DrawingView);
//        drawingView.setOnClickListener(this);
//        GameClock gameClock = new GameClock();
//        gameClock.subscribe(drawingView);
    }
//    @Override
//    public void onGameEvent(GameEvent gameEvent) {
//    }
//    @Override
//    public void onClick(View view) {
//        drawingView.onClick(view);
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //mediaPlayer.start();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mediaPlayer.pause();
//    }

    @Override
    public void onLogIn(String name, String mail, String fac) {
//        GameFragment gameFragment = new GameFragment();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.linearLayout, gameFragment);
//        fragmentTransaction.commit();
    }

    @Override
    public void onDeath(int score) {
        LogInFragment logInFragment = new LogInFragment(this);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout, logInFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
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
}
