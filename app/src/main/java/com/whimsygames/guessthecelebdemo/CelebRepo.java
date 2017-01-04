package com.whimsygames.guessthecelebdemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelebRepo {
    private static String baseUrl = "http://www.posh24.com/celebrities";
    List<Celeb> celebList = new ArrayList<Celeb>();

    public List<Celeb> getFourRandomCelebs() {
        List<Celeb> shortList = new ArrayList<>();
        Collections.shuffle(celebList);
        for (int i = 0 ; i < 4 ; i++) {
            shortList.add(celebList.get(i));
        }
        return shortList;
    }

    public void load() {

        String content = download();
        celebList = contentToList(content);
    }

    private String download() {
        TextDownloader task = new TextDownloader();
        try {

            return task.execute(baseUrl).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO: better logging
        } catch (ExecutionException e) {
            e.printStackTrace();
            //TODO: better logging
        }

        return null;
    }

    private List<Celeb> contentToList(String content) {
        Pattern divPattern = Pattern.compile("<div class=\"image\">(.*?)</div>");
        Matcher divMatcher = divPattern.matcher(content);
        List<Celeb> celebs = new ArrayList<Celeb>();

        String div, url, name;
        while (divMatcher.find()) {
            div = divMatcher.toString();
            url = div.substring(div.indexOf("<img src=\"") + 10, div.indexOf("\" alt="));
            //Log.i("info", "URL=" + url);
            name = div.substring(div.indexOf("\" alt=\"") + 7, div.indexOf("\"/>"));
            //Log.i("info", "Name=" + name);

            celebs.add(new Celeb(url, name));
        }

        return celebs;
    }
}