package com.example.rosen.flappy;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.commons.io.IOUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosen on 12.11.14.
 */
public class LogInFragment extends Fragment implements View.OnClickListener, AsyncResponse{
    private LogInLicener logINlicener;
    private EditText editTextUser;
    private EditText editTextMail;
    private String stringFromRadio;

    public LogInFragment(MyActivity myActivity)
    {
        this.logINlicener = myActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_fragment, container, false);
        Button btnLogIn = (Button) view.findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        editTextUser = (EditText) view.findViewById(R.id.editTextUserName);
        editTextMail = (EditText) view.findViewById(R.id.editTextUserMail);
        if (((RadioButton)view.findViewById(R.id.radioButtonFMi)).isChecked())
        { stringFromRadio = ((RadioButton)view.findViewById(R.id.radioButtonFMi)).getText().toString();}
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return view;
    }
    private JSONObject createJsonObj()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", editTextUser.getText().toString());
            jsonObject.put("mail", editTextMail.getText().toString());
            jsonObject.put("score", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    @Override
    public void onClick(View view) {
//        logINlicener.onLogIn(editTextUser.getText().toString(),
//                editTextMail.getText().toString(), "asd");
        //httpURLConn();
        //httpURL1stPut();
        URL url = null;
        try {
            url = new URL("http://95.111.103.224:8080/Flappy/scores");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        PostMyResult postMyResult = new PostMyResult(url, createJsonObj(), this);
        postMyResult.execute();
        //httpURL1stGet();
    }

//    private void httpURL1stGet()
//    {
//        JSONObject jsonObject = createJsonObj();
//        URL url = null;
//        HttpClient client = new DefaultHttpClient();
//        String getResponeString;
//        try {
//
//            Log.i("didTHeR", "MadeIT");
//            url = new URL("http://95.111.103.224:8080/Flappy/scores");
//            HttpGet get = new HttpGet(String.valueOf(url));
//            getResponeString = get.getAllHeaders().toString();
//            HttpResponse response = client.execute(request);
//            in = new BufferedReader(new InputStreamReader(
//                    response.getEntity().getContent()));
//
//            Log.i("response", getResponeString.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void httpURL1stPut()
    {
        JSONObject jsonObject = createJsonObj();
        URL url = null;
        HttpClient client = new DefaultHttpClient();
        try {

            Log.i("didTHeR", "MadeIT");
            url = new URL("http://95.111.103.224:8080/Flappy/scores");
            HttpPut put= new HttpPut(String.valueOf(url));
            put.setHeader("Content-type", "application/json");
            put.setEntity(new StringEntity(jsonObject.toString()));
            HttpResponse response = client.execute(put);
            Log.i("response", IOUtil.toString(response.getEntity().getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void httpURLConn()
    {
        JSONObject jsonObject = createJsonObj();

        URL url = null;
        HttpURLConnection httpCon = null;
        OutputStreamWriter out = null;

        try {
            url = new URL("http://95.111.103.224:8080/Flappy/scores");
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(boolean output) {
        if (output) {
            //Toast.makeText(this, "ResultPost", Toast.LENGTH_SHORT).show();
            Log.i("asd", "Finish");
        }
        else {
            Log.i("asd", "NotFinish");
        }
    }
}
