package com.example.rosen.sampleflappy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosen on 29.10.14.
 */
public class DrawingView extends ImageView implements GameClock.GameClockListener, View.OnClickListener{
    private Background background;
    private Bird bird;
    private boolean gameOver;
    private int counter;
    private int points;
    private List<Obstacle> obstaclesList;

    public DrawingView(Context context) {
        super(context);
    }
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableBackground = getResources().getDrawable(Settings.BACKGROUND_IMG);
        Drawable drawableBird = getResources().getDrawable(Settings.BIRD_IMG);
        background = new Background(drawableBackground);
        bird = new Bird(drawableBird);
        obstaclesList = new ArrayList<Obstacle>();
        obstaclesList.add( new Obstacle(0, 0));
        counter = 0;
        this.gameOver = false;
        this.points = 0;
    }
    public DrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        this.screanHeith = this.getHeight();
//        this.screanWidth = this.getWidth();
        Settings.DEVICE_HEIGHT= this.getHeight();
        Settings.DEVICE_WIDTH = this.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        background.draw(canvas);
        bird.draw(canvas);
        for(Obstacle obstacle : obstaclesList)
        {
            obstacle.draw(canvas);
        }
        super.onDraw(canvas);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        if (!gameOver)
        {
            background.onGameEvent(gameEvent);
            bird.onGameEvent(gameEvent);
            for(Obstacle obstacle : obstaclesList)
            {
                obstacle.onGameEvent(gameEvent);
            }
            points++;
            counter++;
            if(counter == 150)
            {
                counter = 0;
                obstaclesList.add(new Obstacle(Settings.DEVICE_HEIGHT, Settings.DEVICE_WIDTH));
                //Obstacle(this.screanHeith, this.screanWidth));
            }
            for(Obstacle obstacle : obstaclesList)
            {
                if (Rect.intersects(bird.getBirdRect(), obstacle.getObstacleRect()))
                {
                    Log.i("asd", "Colosion");
                    this.gameOver = true;
                    gameOver();

                }
            }
            invalidate();
        }
    }

    private void gameOver()
    {
        Toast.makeText(getContext(), "Game Over. Total: " + points +" points.", Toast.LENGTH_SHORT).show();
    }

//    private boolean collision(Obstacle obstacle) {
//        if (obstacle.getPosition().y == 0) // Obstacle spawned at the top
//            if (bird.getPosition().y < obstacle.getHeight() &&
//                    bird.getPosition().x + bird.getWidth() >= obstacle.getPosition().x)
//                return true;
//        if (obstacle.getPosition().y != 0) //Obstacle spawned at the bottom
//            if (bird.getPosition().y >= (Settings.DEVICE_HEIGHT - obstacle.getHeight()) &&
//                    bird.getPosition().x + bird.getWidth() >= obstacle.getPosition().x)
//                return true;
//        return false;
//    }

    @Override
    public void onClick(View view) {
        bird.onClick(view);
    }

}

