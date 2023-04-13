package ru.mirea.muravievvr.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import ru.mirea.muravievvr.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-01-20, НОМЕР ПО СПИСКУ: 21, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: \"Кто я?\"");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { new Thread(new Runnable() {
                public void run() {
                    int numberThread = counter++;
                    Log.d("ThreadProject", String.format(
                            "Запущен поток No %d студентом группы No %s номер по списку No %s ", numberThread, "БСБО-01-20","21"));
                    long endTime = System.currentTimeMillis() + 20 * 1000; while (System.currentTimeMillis() < endTime) {
                        synchronized (this) { try {
                            wait(endTime - System.currentTimeMillis());
                            Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime); } catch (Exception e) {
                            throw new RuntimeException(e); }
                        }
                        Log.d("ThreadProject", "Выполнен поток No " + numberThread); }
                } }).start();
            }
        });

        binding.button2.setOnClickListener(v -> {
            new Thread(() -> {
                int days = Integer.parseInt(binding.editTextDays.getText().toString());
                int pairs = Integer.parseInt(binding.editTextPairs.getText().toString());
                float result = (float)pairs / days;
                binding.textView.post(() -> {
                   binding.textView.setText("Среднее кол-во пар: " + Float.toString(result));
                });
            }).start();
        });

        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());
    }
}