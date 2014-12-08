package com.example.rosen.sampleexpenselist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MyActivity extends Activity implements View.OnClickListener {
    private EditText mLabel;
    private EditText mPrice;
    private Button mAddButton;
    private ExpenceListAdapter expenceListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mLabel = (EditText) findViewById(R.id.editTextLabel);
        mPrice = (EditText) findViewById(R.id.editTextPrice);
        mAddButton = (Button) findViewById(R.id.btnAdd);
        mAddButton.setOnClickListener(this);
        ListView expenceListView = (ListView) findViewById(R.id.expenceListView);
        //List<ExpenceProduct> expenceList = Storage.getInstance().getExpenceList();
        //expenceListAdapter = new ExpenceListAdapter(Storage.getInstance().getExpenceList());
        expenceListAdapter = new ExpenceListAdapter();
        expenceListView.setAdapter(expenceListAdapter);
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
//        Storage.getInstance().saveExpenceProduct(
//                new ExpenceProduct(
//                        mLabel.getText().toString(),
//                        mPrice.getText().toString()
//                )
//        );
        expenceListAdapter.add(
                new ExpenceProduct(
                        mLabel.getText().toString(),
                        mPrice.getText().toString()
                )
        );
        expenceListAdapter.notifyDataSetChanged();
    }
}
