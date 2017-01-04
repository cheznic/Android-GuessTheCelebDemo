package com.whimsygames.guessthecelebdemo;


import android.graphics.Bitmap;

import java.util.concurrent.ExecutionException;

public class Celeb {
    private String url;
    private String name;

    public Celeb(String url, String name) {
        setImgUrl(url);
        setName(name);
    }

    public Bitmap getBitmap() {

        ImageDownloader task = new ImageDownloader();
        Bitmap bitmap = null;

        try {
            bitmap = task.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO: better logging

        } catch (ExecutionException e) {
            e.printStackTrace();
            //TODO: better logging

        }
        return bitmap;
    }

    public void setImgUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
