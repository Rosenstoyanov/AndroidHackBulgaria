package com.example.rosen.drawablebrush;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by rosen on 25.10.14.
 */
public class MyCustomView extends ImageView
{
    private Bitmap bitmapToDraw;

    private int myLayOutX, myLayOutY;
    private float x, y;
    private Canvas myCanvas;
    private Bitmap backingBItmap;

    public MyCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        myLayOutX = (int)this.getX();
//        myLayOutY = (int)this.getY();
        backingBItmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(backingBItmap);
        //init();
        //super.onLayout(changed, left, top, right, bottom);
//        myLayOutX = left + right;
//        myLayOutY = top + bottom;
    }

    private void init() {
        //if (flag)
        //{
            //View view = findViewById(R.id.myCustomView);
            backingBItmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
            myCanvas = new Canvas(backingBItmap);
        //}
    }

    public MyCustomView(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmapToDraw != null)
        {
            myCanvas.drawBitmap(bitmapToDraw, x,y,null);

            //canvas.get
            //Log.i("bitMap","bitMap");
            Paint paint = new Paint();
            paint.setAlpha(200);
            canvas.drawBitmap(backingBItmap,0,0,paint);
            //canvas.drawBitmap(null,0,0,paint);
        }

        //super.onDraw(canvas);

    }
    public void paintImage(Drawable drawable, float x, float y)
    {
        //Canvas myCanvas = new Canvas();
        //Bitmap imgToDrowBitMap = ((BitmapDrawable)drawable).getBitmap();
//        Paint paint = new Paint();
//        paint.setAlpha(20);
        //Log.i("paintImage","paintImage");
        this.x = x;
        this.y = y;
        bitmapToDraw = ((BitmapDrawable)drawable).getBitmap();
        //myCanvas.drawBitmap(imgToDrowBitMap,x,y,paint);
    }
}
