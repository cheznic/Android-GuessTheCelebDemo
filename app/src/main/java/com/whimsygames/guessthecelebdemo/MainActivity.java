package com.whimsygames.guessthecelebdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private List<Celeb> celebs;
    CelebRepo repo;
    private static Random random = new Random();
    private int correctIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = new CelebRepo();
        repo.load();

        reset();
    }

    protected void guess(View view) {
        int guessedIndex = Integer.parseInt(view.getTag().toString());

        CharSequence text;

        if (guessedIndex == correctIndex)
            text = "Correct!";
         else
            text = "Wrong! The correct name is " + celebs.get(correctIndex).getName();

        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();

        reset();

    }

    private void reset() {
        celebs = repo.getFourRandomCelebs();
        correctIndex = random.nextInt(4);
        Celeb celeb = celebs.get(correctIndex);
        Bitmap bitmap = celeb.getBitmap();

        View layout = findViewById(R.id.activity_main);
        Button celebView;


        for (int i = 0 ; i < celebs.size() ; i++) {
            celebView = (Button) layout.findViewWithTag(Integer.toString(i));

            celebView.setText(celebs.get(i).getName());
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }
}
