package com.example.rosen.swipegallery;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosen on 19.11.14.
 */
public class ImgGalleryAdapter extends FragmentStatePagerAdapter {
    private List<Drawable> imgList;
    public ImgGalleryAdapter(FragmentManager fm) {
        super(fm);
        imgList = new ArrayList<Drawable>(initList());
    }
    private ArrayList<Drawable> initList()
    {
        ArrayList<Drawable> listImg = new ArrayList<Drawable>();
        File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File[] files = picDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String s) {
                return !new File(dir, s).isDirectory();
            }
        });
        for (File file : files)
        {
//            listImg.add(BitmapFactory.decodeFile(file.getAbsolutePath()));
            listImg.add(Drawable.createFromPath(file.getAbsolutePath()));
        }
        return listImg;
    }

    @Override
    public Fragment getItem(final int i) {
        Fragment fragment = new Fragment()
        {
            @Override
            public void setArguments(Bundle args) {
                //prevent crashing when you do the rotate
                super.setArguments(args);
            }

            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                ImageView imageView = new ImageView(container.getContext());
                imageView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setImageDrawable(imgList.get(i));
                return imageView;
            }
        };
//        fragment.getView().set

//        ImageView imageView = new ImageView();
//        fragment.set
        return fragment;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }
}
