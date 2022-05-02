package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.Activity_for_play;
import ru.sinforge.barabashka_game.R;
import ru.sinforge.barabashka_game.menu_fragments.GameRules;
import ru.sinforge.barabashka_game.menu_fragments.Settings;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btn_settings = findViewById(R.id.button_settings);
        btn_settings.setOnClickListener(this::onClick);
        Button btn_play = findViewById(R.id.button_play);
        btn_play.setOnClickListener(this::onClick);
        Button btn_exit = findViewById(R.id.button_exit);
        btn_exit.setOnClickListener(this::onClick);
        Button btn_rules = findViewById(R.id.button_rules);
        btn_rules.setOnClickListener(this::onClick);

    }
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.button_play:
                intent = new Intent(this, Activity_for_play.class);
                startActivity(intent);
                break;
            case R.id.button_rules:
                intent = new Intent(this, GameRulesActivity.class);
                startActivity(intent);

                break;
            case R.id.button_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }

    }
}