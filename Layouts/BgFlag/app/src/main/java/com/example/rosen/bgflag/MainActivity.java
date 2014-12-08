package com.example.rosen.bgflag;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button whiteBtn, greenBtn, redBtn;
    private int[] rainbow;
    private int indexWhite, indexGreen, indexRed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whiteBtn = (Button) findViewById(R.id.whiteBtn);
        greenBtn = (Button) findViewById(R.id.greenBtn);
        redBtn = (Button) findViewById(R.id.redBtn);
        indexWhite = 0;
        indexGreen = 0;
        indexRed = 0;
        whiteBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        redBtn.setOnClickListener(this);
        rainbow = getResources().getIntArray(R.array.rainbow);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (view.getId() == R.id.whiteBtn)
        {
            whiteBtn.setBackgroundColor(rainbow[indexWhite]);
            indexWhite++;
            if (indexRed == rainbow.length)
            {
                indexRed = 0;
            }
        }
        if (view.getId() == R.id.greenBtn)
        {
            greenBtn.setBackgroundColor(rainbow[indexGreen]);
            indexGreen++;
            if (indexRed == rainbow.length)
            {
                indexRed = 0;
            }
        }
        if (view.getId() == R.id.redBtn)
        {
            redBtn.setBackgroundColor(rainbow[indexRed]);
            indexRed++;
            if (indexRed == rainbow.length)
            {
                indexRed = 0;
            }
        }
    }
}
