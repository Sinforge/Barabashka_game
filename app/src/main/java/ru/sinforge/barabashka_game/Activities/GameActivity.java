package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ru.sinforge.barabashka_game.GameComponents.GameView;
import ru.sinforge.barabashka_game.GameComponents.PauseFragment;
import ru.sinforge.barabashka_game.Services.MusicService;
import ru.sinforge.barabashka_game.R;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MusicService.class));
        setContentView(R.layout.activity_for_play);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        String player1 = getIntent().getStringExtra("player1");
        String player2 = getIntent().getStringExtra("player2");
        String point = getIntent().getStringExtra("point_to_win");
        int point_to_win = Integer.valueOf(point);
        GameView gameView = findViewById(R.id.surfaceView1);
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

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }
}