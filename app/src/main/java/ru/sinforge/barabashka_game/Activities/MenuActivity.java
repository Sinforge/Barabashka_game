package ru.sinforge.barabashka_game.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.Activities.GameHistory.GamesHistoryActivity;
import ru.sinforge.barabashka_game.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Button btn_play = findViewById(R.id.button_play);
        btn_play.setOnClickListener(this::onClick);
        Button btn_exit = findViewById(R.id.button_exit);
        btn_exit.setOnClickListener(this::onClick);
        Button btn_rules = findViewById(R.id.button_rules);
        btn_rules.setOnClickListener(this::onClick);
        Button btn_history = findViewById(R.id.history);
        btn_history.setOnClickListener(this::onClick);

    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.history:
                intent = new Intent(this, GamesHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.button_play:
                intent = new Intent(this, GameMode.class);
                startActivity(intent);
                break;
            case R.id.button_rules:
                intent = new Intent(this, GameRulesActivity.class);
                startActivity(intent);
                break;
            case R.id.button_exit:
                this.finish();
                System.exit(0);
                break;
        }

    }
}