package com.example.rosen.flappy;

import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;

/**
 * Created by rosen on 12.11.14.
 */
public class GameFragment extends Fragment implements View.OnClickListener, GameOver{
    private MediaPlayer mediaPlayer;
    private DrawingView drawingView;
    private LogInLicener logINlicener;

    public GameFragment(LogInLicener myActivity)
    {
        this.logINlicener = myActivity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        drawingView = (DrawingView) inflater.inflate(R.layout.game_activity, container, false);
       // View view = inflater.inflate(R.layout.game_activity, container, false);
        //view.setOnClickListener(this);
        drawingView.setGameFragment(this);
        mediaPlayer = MediaPlayer.create(drawingView.getContext(), R.raw.prey_overture);
        mediaPlayer.setLooping(true);
        drawingView.setOnClickListener(this);
        GameClock gameClock = new GameClock();
        gameClock.subscribe(drawingView);
        //gameClock.subscribe(this);

        //gameClock.subscribe((DrawingView)view);

        //return view;
        return drawingView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    public void onClick(View view) {
        drawingView.onClick(view);
        //InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethod.HIDE_NOT_ALWAYS);
    }

    @Override
    public void gameOver(int score) {
        logINlicener.onDeath(score);
    }
}
