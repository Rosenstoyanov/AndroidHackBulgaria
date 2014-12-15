package com.example.rosen.camera2email;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.net.URI;


public class MyActivity extends Activity {
    private String mPhotoPath;
    private static final String IMG_NAME= "img.png";
    private static final int REQUEST_CODE_CAMERA = 564;
    private URI uri;
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        createImg();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if (requestCode == RESULT_OK){
                    Uri uri = data.getData();
                    Intent mailIntent = new Intent(Intent.ACTION_SEND);
                    mailIntent.setType("message/rfc822");
                    mailIntent.putExtra(Intent.EXTRA_EMAIL, "someMail@mail.bg");
                    mailIntent.putExtra(Intent.EXTRA_SUBJECT, "mailSubject");
                    mailIntent.putExtra(Intent.EXTRA_TEXT, "Some text to send");
                    mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
                    //uri kum snimkata
                    startActivity(Intent.createChooser(mailIntent, "Send mail....."));
                }
                break;
        }
    }

    private void createImg()
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMG_NAME);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
    }

    private File createImageFile() {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return null;
        }
        String fileName = "IMG_PROFILE";
        File storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image;
        try {
            image = File.createTempFile(
                    fileName, /* prefix */
                    ".png", /* suffix */
                    storageDir /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        mPhotoPath = image.getAbsolutePath();
        return image;
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
