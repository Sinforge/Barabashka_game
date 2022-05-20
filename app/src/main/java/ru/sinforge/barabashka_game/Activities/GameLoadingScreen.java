package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.R;

public class GameLoadingScreen extends AppCompatActivity {

    private static final int SPLASH_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_loading_screen);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        ImageView ghost = findViewById(R.id.ghost);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.ghost_moving);
        ghost.startAnimation(animation);
        new Handler().postDelayed(() -> {
            Intent gameIntent = new Intent(GameLoadingScreen.this, Activity_for_play.class);




            gameIntent.putExtra("point_to_win", getIntent().getStringExtra("point_to_win") );
            gameIntent.putExtra("player1", getIntent().getStringExtra("player1"));
            gameIntent.putExtra("player2", getIntent().getStringExtra("player2"));
            startActivity(gameIntent);
            finish();
        }, SPLASH_TIME);
    }
}