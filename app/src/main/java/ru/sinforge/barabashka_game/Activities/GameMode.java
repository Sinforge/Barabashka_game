package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.Activities.GameHistory.GamesHistoryActivity;
import ru.sinforge.barabashka_game.R;

public class GameMode extends AppCompatActivity {
    private String Player1_NAME = "Player1";
    private String Player2_NAME = "Player2";
    private int points_to_end = 15;
    public EditText player1;
    public EditText player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        Button b_10 = findViewById(R.id.number_10);
        Button b_15 = findViewById(R.id.number_15);
        Button b_20 = findViewById(R.id.number_20);
        Button b_25 = findViewById(R.id.number_25);
        player1 = findViewById(R.id.player1_field);
        player2 = findViewById(R.id.player2_field);
        Button start_game = findViewById(R.id.starting);


        b_10.setOnClickListener(this::onClick);
        b_15.setOnClickListener(this::onClick);
        b_20.setOnClickListener(this::onClick);
        b_25.setOnClickListener(this::onClick);
        start_game.setOnClickListener(this::onClick);





    }


    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.number_10:
                this.points_to_end = 10;
                Toast.makeText(this, "10 установлено конечным счетом", Toast.LENGTH_SHORT).show();
                break;
            case R.id.number_15:
                this.points_to_end = 15;
                Toast.makeText(this, "15 установлено конечным счетом", Toast.LENGTH_SHORT).show();

                break;
            case R.id.number_20:
                this.points_to_end = 20;
                Toast.makeText(this, "20 установлено конечным счетом", Toast.LENGTH_SHORT).show();
                break;
            case R.id.number_25:
                this.points_to_end = 25;
                Toast.makeText(this, "25 установлено конечным счетом", Toast.LENGTH_SHORT).show();
                break;
            case R.id.starting:
                Player1_NAME = player1.getText().toString();
                Player2_NAME = player2.getText().toString();
                Intent intent1 = new Intent(this, Activity_for_play.class);
                intent1.putExtra("point_end", points_to_end);
                intent1.putExtra("player1", Player1_NAME);
                intent1.putExtra("player2", Player2_NAME);
                startActivity(intent1);
                break;
        }

    }
}