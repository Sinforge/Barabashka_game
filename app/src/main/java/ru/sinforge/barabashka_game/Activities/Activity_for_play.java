package ru.sinforge.barabashka_game.Activities;

import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.GameComponents.GameView;
import ru.sinforge.barabashka_game.R;

public class Activity_for_play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_play);
    }
}