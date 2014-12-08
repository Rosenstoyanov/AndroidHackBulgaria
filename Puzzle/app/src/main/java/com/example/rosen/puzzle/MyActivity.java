package com.example.rosen.puzzle;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MyActivity extends Activity{

//    Resources res = getResources();
//    TypedArray icons = res.obtainTypedArray(R.array.icons);
//    Drawable drawable = icons.getDrawable(0);
//
//    TypedArray colors = res.obtainTypedArray(R.array.colors);
//    int color = colors.getColor(0,0);

//    Button myButton = new Button(this);
//    myButton.setLayoutParams(new LinearLayout.LayoutParams(
//    LinearLayout.LayoutParams.FILL_PARENT,
//    LinearLayout.LayoutParams.FILL_PARENT));
//
//    myLayout.addView(myButton);
    ArrayList<Drawable> drawablesList;
    ArrayList<Drawable> currDrawables;
    ArrayList<ImageView> imageViews;
    ArrayList<Drawable> originalOrder;
    LinearLayout mainLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);
        initializeDrawables();
        imageViews = new ArrayList<ImageView>();
        Collections.shuffle(drawablesList);
        int numberOfImg = 0;
        for (int j = 0; j < 4; j++) {
            LinearLayout horLayout = new LinearLayout(this);
            horLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            horLayout.setOrientation(LinearLayout.HORIZONTAL);
            mainLinearLayout.addView(horLayout);
            for (int i = numberOfImg; i < Math.sqrt(drawablesList.size()) + numberOfImg; i++) {
                final ImageView iV = new ImageView(this);
                imageViews.add(iV);
                iV.setImageDrawable(drawablesList.get(i));
                iV.setLayoutParams(new ViewGroup.LayoutParams(120, 120));
                iV.setScaleType(ImageView.ScaleType.FIT_XY);
                iV.setPadding(1,1,1,1);
                iV.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        View.DragShadowBuilder shadow = new View.DragShadowBuilder(view);
                        view.startDrag(null, shadow, view, 0);
                        return false;
                    }
                });
                iV.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View view, DragEvent dragEvent) {
                        if (dragEvent.getAction() == DragEvent.ACTION_DROP){
                            ImageView dragSource = (ImageView) dragEvent.getLocalState();
                            ImageView currView = (ImageView) view;
                            Drawable draggedSourceDrawable = dragSource.getDrawable();

                            dragSource.setImageDrawable(currView.getDrawable());
                            currView.setImageDrawable(draggedSourceDrawable);
                            //orderImg();
                            if (checkIfUserWin())
                            {
                                Toast.makeText(getApplicationContext(),"you Won",Toast.LENGTH_LONG).show();
                            }
                        }
                        return true;
                    }
                });
                horLayout.addView(iV);
            }
            numberOfImg+= Math.sqrt(drawablesList.size());
        }
    }

    private void orderImg() {
        for (int i = 0; i < imageViews.size(); i++)
        {
            imageViews.get(i).setImageDrawable(originalOrder.get(i));
        }
    }

    private boolean checkIfUserWin()
    {
        currDrawables = new ArrayList<Drawable>();
        for (int i = 0; i < imageViews.size(); i++)
        {
            currDrawables.add(imageViews.get(i).getDrawable());
        }
        boolean flag = originalOrder.equals(currDrawables);
        boolean flag2 = originalOrder.containsAll(currDrawables);
        boolean flag3 = originalOrder.size() == drawablesList.size();
        return flag;
    }
    private void initializeDrawables()
    {
        drawablesList = new ArrayList<Drawable>();
        originalOrder = new ArrayList<Drawable>();
        TypedArray slices = getResources().obtainTypedArray(R.array.slicedImg);
        for (int i = 0; i < slices.length(); i++)
        {
            drawablesList.add(slices.getDrawable(i));
            originalOrder.add(slices.getDrawable(i));
        }
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
