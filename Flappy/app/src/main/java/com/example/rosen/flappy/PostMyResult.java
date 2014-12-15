package com.example.rosen.flappy;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by rosen on 15.11.14.
 */
public class PostMyResult extends AsyncTask {
    private URL url;
    private JSONObject jsonObject;
    private AsyncResponse asyncResponse;
    public PostMyResult(URL url, JSONObject jsonObject, AsyncResponse asyncResponse)
    {
        this.url = url;
        this.jsonObject = jsonObject;
        this.asyncResponse = asyncResponse;
    }
//    private void httpURL1stPut(URL url, JSONObject jsonObject)
//    {
//        HttpClient client = new DefaultHttpClient();
//        try {
//
//            Log.i("didTHeR", "MadeIT");
//            HttpPut put= new HttpPut(String.valueOf(url));
//            put.setEntity(new StringEntity(jsonObject.toString()));
//            HttpResponse response = client.execute(put);
//
//            Log.i("response", response.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected Object doInBackground(Object[] objects) {
        //httpURL1stPut(url, jsonObject);
        HttpClient client = new DefaultHttpClient();
        try {

            Log.i("didTHeR", "MadeIT");
            HttpPut put= new HttpPut(String.valueOf(url));
            put.setEntity(new StringEntity(jsonObject.toString()));
            HttpResponse response = client.execute(put);

            Log.i("response", response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Object o) {
        asyncResponse.processFinish(((Boolean)o).booleanValue());
    }
}
