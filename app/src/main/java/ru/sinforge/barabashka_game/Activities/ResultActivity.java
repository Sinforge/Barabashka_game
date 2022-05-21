package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.Database.AppDatabase;
import ru.sinforge.barabashka_game.Database.Result;
import ru.sinforge.barabashka_game.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        TextView winner = findViewById(R.id.winner_name);
        MediaPlayer player = MediaPlayer.create(this, R.raw.end_of_the_game);
        player.setLooping(true);
        player.start();


        String winner_name = getIntent().getStringExtra("Winner");
        winner.setText(winner_name);

        int score1 = getIntent().getIntExtra("Score1", -10);
        int score2 = getIntent().getIntExtra("Score2", -10);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);



        saveNewResult(winner_name, score1, score2, dateText);




        Button btn_back_to_menu = findViewById(R.id.result_to_menu);
        btn_back_to_menu.setOnClickListener(v-> {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            player.stop();
        });




        Button ShareResult = findViewById(R.id.share_result);
        ShareResult.setOnClickListener(v-> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, winner_name + getString(R.string.share_r));
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

    }
    private void saveNewResult(String winner, int score1, int score2, String date) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Result result = new Result();
        result.winner = winner;
        result.score1 = score1;
        result.score2 = score2;
        result.date = date;
        db.resultDAO().insertResult(result);

    }
}