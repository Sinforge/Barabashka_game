package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuIntent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(menuIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}