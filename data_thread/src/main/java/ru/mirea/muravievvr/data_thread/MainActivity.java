package ru.mirea.muravievvr.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.muravievvr.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvInfo.setText("\n1. runOnUiThread выполнится мгновенно если запущено из UiThread, " +
                "и будет поставлено в очередь на выполнение если запущено из параллельного потока\n" +
                "2. post всегда будет поставлено в очередь на выполнение (не зависимо от потока) \n" +
                "3. postDelayed - то же самое, что и post, только с задержкой");

        binding.tvInfo.append("\n\nПорядок: \n");

        final Runnable runn1 = () -> binding.tvInfo.append("runn1\n");
        final Runnable runn2 = () -> binding.tvInfo.append("runn2\n");
        final Runnable runn3 = () -> binding.tvInfo.append("runn3\n");

        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                runOnUiThread(runn1);
                TimeUnit.SECONDS.sleep(1);
                binding.tvInfo.postDelayed(runn3, 2000);
                binding.tvInfo.post(runn2);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}