package com.example.rosen.sampleflappy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by rosen on 29.10.14.
 */
public class Background extends GameObject implements View.OnClickListener, GameClock.GameClockListener{
    private Bitmap backgroundImg;
    private int leftFirst, leftSecond, rightFirst, rightSecond;
    private Rect src;
    private Rect destFirst;
    private Rect destSecond;
    private Paint paint;

    Background(Drawable drawable)
    {
        backgroundImg = ((BitmapDrawable)drawable).getBitmap();
        src = new Rect(0, 0, backgroundImg.getWidth(), backgroundImg.getHeight());
        destFirst = new Rect();
        destSecond = new Rect();
        rightFirst = 0;
        rightSecond = 0;
        paint = new Paint();
    }

    public void draw(Canvas canvas)
    {
        if (canvas.getWidth() + this.rightFirst == 0)
        {
            this.rightFirst = canvas.getWidth();
        }
        if ((canvas.getWidth() * 2) - this.rightSecond == 0)
        {
            this.rightSecond = 0;
        }
        destFirst.set(
                this.rightFirst,
                Settings.ZERO_COORD_CONSTANT,
                (canvas.getWidth() * 2) + this.rightFirst,
                canvas.getHeight());
        destSecond.set(
                canvas.getWidth() - this.rightSecond,
                Settings.ZERO_COORD_CONSTANT,
                (canvas.getWidth() * 2) - this.rightSecond,
                canvas.getHeight());
        canvas.drawBitmap(backgroundImg, src, destFirst, paint);
        canvas.drawBitmap(backgroundImg, src, destSecond, paint);
    }
    @Override
    public void onGameEvent(GameEvent gameEvent) {
        super.onGameEvent(gameEvent);
        this.rightFirst -= Settings.BACKGROUND_SPEED_CONSTANT;
        this.rightSecond += Settings.BACKGROUND_SPEED_CONSTANT;
    }
    @Override
    public PointF getPosition() {
        PointF pointF = new PointF(0,0);
        return pointF;
    }

    @Override
    public int getWidth() {
        return backgroundImg.getWidth();
    }

    @Override
    public int getHeight() {
        return backgroundImg.getHeight();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
