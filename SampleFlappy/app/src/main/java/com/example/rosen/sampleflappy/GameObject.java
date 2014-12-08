package com.example.rosen.sampleflappy;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by rosen on 29.10.14.
 */
public abstract class  GameObject implements View.OnClickListener, GameClock.GameClockListener{
    public abstract void draw(Canvas canvas);
    public abstract PointF getPosition();
    public abstract int getWidth();
    public abstract int getHeight();
    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }
}
