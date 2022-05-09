package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ru.sinforge.barabashka_game.GameComponents.GameView;
import ru.sinforge.barabashka_game.GameComponents.PauseFragment;
import ru.sinforge.barabashka_game.R;

public class Activity_for_play extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_play);
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