package com.example.rosen.updatedpuzzel;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

//array ot ID-ta
public class MyActivity extends Activity {

    private GridLayout mGridLayout;
    ArrayList<Drawable> drawablesList;
    ArrayList<Drawable> currDrawables;
    ArrayList<ImageView> imageViews;
    ArrayList<Drawable> originalOrder;
    ArrayList<ImageView> imageViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout);
        initializeDrawables();

        imageViews = new ArrayList<ImageView>();
        Collections.shuffle(drawablesList);

        for (int i = 0; i < drawablesList.size(); i++)
        {
            final ImageView iV = new ImageView(this);
            imageViews.add(iV);
            iV.setImageDrawable(drawablesList.get(i));
            iV.setLayoutParams(new ViewGroup.LayoutParams(120, 120));
            iV.setScaleType(ImageView.ScaleType.FIT_XY);
            iV.setPadding(1,1,1,1);
            iV.setTag(i);
            //Animator anim = new Animator();
//            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(myContext,
//                    R.anim.property_animator);
//            set.setTarget(myObject);
//            set.start();
            iV.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    View.DragShadowBuilder shadow = new View.DragShadowBuilder(view);
                    view.startDrag(null, shadow, view, 0);
//                    Toast.makeText(getApplicationContext(),view.getTag().toString(),Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            iV.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
                        ImageView dragSource = (ImageView) dragEvent.getLocalState();
                        ImageView currView = (ImageView) view;
                        //Drawable draggedSourceDrawable = dragSource.getDrawable();

                        PropertyValuesHolder propXSource = PropertyValuesHolder.ofFloat("x", dragSource.getX());
                        PropertyValuesHolder propYSource = PropertyValuesHolder.ofFloat("y", dragSource.getY());
                        PropertyValuesHolder propXCurrView = PropertyValuesHolder.ofFloat("x", currView.getX());
                        PropertyValuesHolder propYCurrView = PropertyValuesHolder.ofFloat("y", currView.getY());
                        Animator anim = ObjectAnimator.ofPropertyValuesHolder(currView, propXSource, propYSource);
                        anim.start();
                        Animator animSource = ObjectAnimator.ofPropertyValuesHolder(dragSource, propXCurrView, propYCurrView);
                        animSource.start();

                        //dragSource.setImageDrawable(currView.getDrawable());
                        //currView.setImageDrawable(draggedSourceDrawable);
                        //orderImg();
                        if (checkIfUserWin()) {
                            Toast.makeText(getApplicationContext(), "you Won", Toast.LENGTH_LONG).show();
                        }
                    }
                    //orderImg();
                    return true;
                }
            });
            //imageViewList.add(iV);
            mGridLayout.addView(iV,i);
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
//        for (int i = 0; i < imageViews.size(); i++)
//        {
//            currDrawables.add(imageViews.get(i).getDrawable());
//        }
        boolean sth = false;
        for (int i = 0; i < mGridLayout.getChildCount(); i++)
        {
            ImageView iv = (ImageView) mGridLayout.getChildAt(i);
            currDrawables.add(iv.getDrawable());
            if (originalOrder.get(i) != iv.getDrawable())
            {
                sth = false;
            }

//            Toast.makeText(getApplicationContext(),iv.getTag().toString(),Toast.LENGTH_SHORT).show();
        }

        boolean flag = originalOrder.equals(currDrawables);
//        Toast.makeText(getApplicationContext(),Boolean.toString(sth),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),Boolean.toString(flag),Toast.LENGTH_SHORT);

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
