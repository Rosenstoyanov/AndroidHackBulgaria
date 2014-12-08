package com.example.rosen.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends Activity implements View.OnClickListener{
    private EditText mEditTextInput;
    private Button mBtnDial;
    private Button mBtnBrowse;
    private Button mBtnSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mEditTextInput = (EditText) findViewById(R.id.editTextInput);
        mBtnBrowse = (Button) findViewById(R.id.btnBrowse);
        mBtnDial = (Button) findViewById(R.id.btnDial);
        mBtnSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        mBtnBrowse.setOnClickListener(this);
        mBtnDial.setOnClickListener(this);
        mBtnSetAlarm.setOnClickListener(this);
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

    private void makeCall(String phoneNumber){
        String uri = "tel:" + phoneNumber.trim();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    private void browse(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + url.trim()));
        startActivity(browserIntent);
    }

    private void setAlarm(String setAlarm){
        String[] alarm = setAlarm.split(":");
        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR,Integer.parseInt(alarm[0]));
        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,Integer.parseInt(alarm[1]));
        startActivity(alarmIntent);
    }
    public void clickButton(View view){
        final int id = view.getId();
        if (id == mBtnDial.getId()){
            makeCall(mEditTextInput.getText().toString());
        }
        if (id == mBtnBrowse.getId()){
            browse(mEditTextInput.getText().toString());
        }
        if (id == mBtnSetAlarm.getId()){
            setAlarm(mEditTextInput.getText().toString());
        }
    }
    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == mBtnDial.getId()){
            makeCall(mEditTextInput.getText().toString());
        }
        if (id == mBtnBrowse.getId()){
            browse(mEditTextInput.getText().toString());
        }
        if (id == mBtnSetAlarm.getId()){
            setAlarm(mEditTextInput.getText().toString());
        }
    }
}
