package com.example.rosen.sampleflappy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * Created by rosen on 29.10.14.
 */
public class Bird extends GameObject implements View.OnClickListener, GameClock.GameClockListener {
    private Bitmap birdDraw;
    private int x, y;
    private int moveUpCounter;
    private Rect dest;
    private Rect src;

    Bird(Drawable drawable)
    {
        birdDraw = ((BitmapDrawable)drawable).getBitmap();
        moveUpCounter = 0;
        src = new Rect(0, 0, birdDraw.getWidth(), birdDraw.getHeight());
        dest = new Rect(Settings.BIRD_X,
                Settings.BIRD_Y,
                Settings.BIRD_X + birdDraw.getWidth(),
                Settings.BIRD_Y + birdDraw.getHeight());
        this.x = Settings.BIRD_X;
        this.y = Settings.BIRD_Y;
    }
    @Override
    public void draw(Canvas canvas) {
//        if (y > canvas.getHeight() - birdDraw.getHeight())
//            y = canvas.getHeight() - birdDraw.getHeight();
//        if (-y > birdDraw.getHeight())
//            y = birdDraw.getHeight();
//
//        canvas.drawBitmap(birdDraw, this.x, this.y, null);
//        Paint p = new Paint();
//        p.setColor(Color.BLACK);
        canvas.drawBitmap(birdDraw, src, dest, null);
//        canvas.drawRect(dest,p);
//        Log.i("asd", " "+ this.y);
    }
    public Rect getBirdRect()
    {
        return dest;
    }
    @Override
    public PointF getPosition() {
        return new PointF(this.x, this.y);
    }

    @Override
    public int getWidth() {
        return this.birdDraw.getWidth();
    }

    @Override
    public int getHeight() {
        return this.birdDraw.getHeight();
    }

    @Override
    public void onClick(View view) {
        moveUpCounter = 0;
        dest.offset(0, -50);
        Log.i("asd", "CCCC");
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        super.onGameEvent(gameEvent);
        //dest.offset(0, this.y);
        dest.offset(0, 2);
        if(moveUpCounter <= 15)
            moveBirdUp();
        if (-y > this.birdDraw.getHeight())
            y = birdDraw.getHeight();
        if(y > Settings.DEVICE_HEIGHT - birdDraw.getHeight()) {
            y = Settings.DEVICE_HEIGHT - birdDraw.getHeight();
        }
        moveBirdDown();
        //dest.offset(0, y);
    }

    private void moveBirdUp() {
        y -= Settings.BIRD_JUMP_DISTANCE_CONSTANT + moveUpCounter * 2;
        moveUpCounter += 1;

//        dest.offset(0, y);
    }

    private void moveBirdDown(){
        y = y + Settings.BIRD_FALL_SPEED_CONSTANT;

        //dest.offset(0, y);
    }

}
