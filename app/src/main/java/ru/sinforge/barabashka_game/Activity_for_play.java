package ru.sinforge.barabashka_game;

import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_for_play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GameView(this));
    }
}