package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.R;

public class GameMode extends AppCompatActivity {
    private int points_to_end = 15;
    public EditText player1;
    public EditText player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Button b_10 = findViewById(R.id.number_10);
        Button b_15 = findViewById(R.id.number_15);
        Button b_20 = findViewById(R.id.number_20);
        Button b_25 = findViewById(R.id.number_25);
        player1 = findViewById(R.id.player1_field);
        player2 = findViewById(R.id.player2_field);
        Button start_game = findViewById(R.id.starting);

        Button button = findViewById(R.id.back1);
        button.setOnClickListener(v-> {
            finish();
        });


        b_10.setOnClickListener(this::onClick);
        b_15.setOnClickListener(this::onClick);
        b_20.setOnClickListener(this::onClick);
        b_25.setOnClickListener(this::onClick);
        start_game.setOnClickListener(this::onClick);





    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.number_10:
                this.points_to_end = 10;
                Toast.makeText(this, R.string.set_ten, Toast.LENGTH_SHORT).show();
                break;
            case R.id.number_15:
                this.points_to_end = 15;
                Toast.makeText(this, R.string.set_15, Toast.LENGTH_SHORT).show();

                break;
            case R.id.number_20:
                this.points_to_end = 20;
                Toast.makeText(this, R.string.set_20, Toast.LENGTH_SHORT).show();
                break;
            case R.id.number_25:
                this.points_to_end = 25;
                Toast.makeText(this, R.string.set_25, Toast.LENGTH_SHORT).show();
                break;
            case R.id.starting:
                String player1_NAME = player1.getText().toString();
                String player2_NAME = player2.getText().toString();
                if(player1_NAME.equals("") || player2_NAME.equals("")) {
                    Toast.makeText(this, R.string.players_need_names, Toast.LENGTH_LONG).show();
                    break;
                }
                Intent intent1 = new Intent(this, GameLoadingScreen.class);
                intent1.putExtra("point_to_win", points_to_end + "");
                intent1.putExtra("player1", player1_NAME);
                intent1.putExtra("player2", player2_NAME);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent1);
                break;
        }

    }
}