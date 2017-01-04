package com.whimsygames.guessthecelebdemo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TextDownloader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        String content = "";

        URL url;
        try {
            url = new URL(urls[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            InputStreamReader inputStream = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(inputStream);

            StringBuilder stringBuilder = new StringBuilder();

            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            inputStream.close();
            reader.close();

            content = stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            //TODO: better logging
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: better logging
        }
        return content;
    }
}
