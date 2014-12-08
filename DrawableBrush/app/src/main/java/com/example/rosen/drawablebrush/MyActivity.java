package com.example.rosen.drawablebrush;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MyActivity extends Activity implements View.OnClickListener {

    MyCustomView myCustomView;
    ImageView ImgViewPicOne, ImgViewPicTwo, ImgViewPicThree, selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ImgViewPicOne = (ImageView) findViewById(R.id.imgViewAudi);
        ImgViewPicTwo = (ImageView) findViewById(R.id.imgViewPen);
        ImgViewPicThree = (ImageView) findViewById(R.id.imgViewChrome);
        ImgViewPicOne.setOnClickListener(this);
        ImgViewPicTwo.setOnClickListener(this);
        ImgViewPicThree.setOnClickListener(this);
        myCustomView = (MyCustomView) findViewById(R.id.myCustomView);
        myCustomView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("onTouch","onTouch");
                switch (motionEvent.getActionMasked())
                {
                    case MotionEvent.ACTION_DOWN:
                        myCustomView.paintImage(selectedView.getDrawable(),motionEvent.getX(),motionEvent.getY());
                        myCustomView.invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        myCustomView.paintImage(selectedView.getDrawable(),motionEvent.getX(),motionEvent.getY());
                        myCustomView.invalidate();
                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        pointerX = motionEvent.getX();
//                        pointerY = motionEvent.getY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        pointerX = motionEvent.getX();
//                        pointerY = motionEvent.getY();
//                        break;
                }
                return true;
            }
        });
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
        if (view.getId() == R.id.imgViewAudi)
        {
            Log.i("audi", "audi");
            selectedView = ImgViewPicOne;
        }
        if (view.getId() == R.id.imgViewPen)
        {
            selectedView = ImgViewPicTwo;
        }
        if (view.getId() == R.id.imgViewChrome)
        {
            selectedView = ImgViewPicThree;
        }
    }
}
