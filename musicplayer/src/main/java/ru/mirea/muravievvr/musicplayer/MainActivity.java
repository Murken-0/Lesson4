package ru.mirea.muravievvr.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ru.mirea.muravievvr.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding bindings;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);


        bindings.nextButton.setOnClickListener(v -> {
            Log.d(TAG, "Next");
        });
        bindings.playButton.setOnClickListener(v -> {
            Log.d(TAG, "Play");
        });
        bindings.prevButton.setOnClickListener(v -> {
            Log.d(TAG, "Prev");
        });
    }
}