package com.example.rosen.sampleflappy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by rosen on 08.11.14.
 */
public class Obstacle extends GameObject implements GameClock.GameClockListener {
    private Rect mRect;
    private Paint paint;
    Obstacle(int viewHeight, int viewWidth)
    {
        Random random = new Random();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        mRect = new Rect();
        //mRect.set(400,100, 800, 700);
        if (random.nextBoolean())
        {
            //left bootm rigth top
            mRect.set(
                    viewWidth - Settings.OBSTACLE_WIDTH,
                    viewHeight,
                    viewWidth,
                    viewHeight - Settings.OBSTACLE_MIN_LENGTH + random.nextInt(Settings.OBSTACLE_MAX_LENGTH));
            //mRect.set(viewWidth - 50, viewHeigth, viewWidth, viewHeight - 400);
        }
        else {
            mRect.set(viewWidth - Settings.OBSTACLE_WIDTH,
                    0,
                    viewWidth,
                    viewHeight - Settings.OBSTACLE_MIN_LENGTH + random.nextInt(Settings.OBSTACLE_MAX_LENGTH));
        }
    }

    public Rect getObstacleRect()
    {
        return mRect;
    }
    public void setWidth(int viewHeight, int viewWidth)
    {
        mRect.set(viewWidth - Settings.OBSTACLE_WIDTH, 0, viewWidth, viewHeight - 400);
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(mRect,paint);
    }

    @Override
    public PointF getPosition() {
        //top
        PointF p = new PointF(mRect.left, mRect.bottom);
        return p;
    }

    @Override
    public int getWidth() {
        return (int)mRect.width();
    }

    @Override
    public int getHeight() {
        return (int)mRect.height();
    }
    @Override
    public void onGameEvent(GameEvent gameEvent) {
        super.onGameEvent(gameEvent);
        mRect.offset(-1 * Settings.BACKGROUND_SPEED_CONSTANT, 0);
    }
}
