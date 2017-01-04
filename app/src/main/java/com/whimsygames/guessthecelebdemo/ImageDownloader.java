package com.whimsygames.guessthecelebdemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... urls) {

        Bitmap bitmap = null;

        try {
            URL url = new URL(urls[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            InputStream stream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(stream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            //TODO: better logging
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: better logging
        }

        return bitmap;
    }
}