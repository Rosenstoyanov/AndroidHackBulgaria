package com.example.rosen.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
//        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE).edit();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

//        Intent startLoad = new Intent(this, LoadingActivity.class);
//        startActivity(startLoad);
        if (sharedPref.getBoolean("IsUser", true)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent startLoad = new Intent(MyActivity.this, LoadingActivity.class);
                    startActivity(startLoad);

                    finish();
                }
            }, 3*1000);
            editor.putBoolean("IsUser", false);
            editor.commit();
        }else {
            Intent startLoad = new Intent(MyActivity.this, LoadingActivity.class);
            startActivity(startLoad);
            finish();
        }
    }

//    imgScr = (ImageView) findViewById(R.id.imgViewScr);
//    Drawable d = getResources().getDrawable(R.drawable.splashscreen);
//    imgScr.setImageDrawable(d);
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
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
