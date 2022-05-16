package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ru.sinforge.barabashka_game.GameComponents.GameView;
import ru.sinforge.barabashka_game.GameComponents.PauseFragment;
import ru.sinforge.barabashka_game.Services.MusicService;
import ru.sinforge.barabashka_game.R;

public class Activity_for_play extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MusicService.class));
        setContentView(R.layout.activity_for_play);
        String player1 = getIntent().getStringExtra("player1");
        String player2 = getIntent().getStringExtra("player2");
        int point_to_win = getIntent().getIntExtra("points_to_end", 20);
        GameView gameView = findViewById(R.id.surfaceView1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameView.GameParams(player1, player2, point_to_win);


        // Do something in response to button click
        Button pause = findViewById(R.id.btn);
        pause.setOnClickListener(v -> {

            FragmentManager fragmentManager = getSupportFragmentManager();
            PauseFragment pauseFragment = new PauseFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.place_holder, pauseFragment);
            fragmentTransaction.addToBackStack("MenuIsOpen");
            fragmentTransaction.commit();
        });
    }
}