package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.R;

import static android.content.Intent.ACTION_SEND;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView winner = findViewById(R.id.winner_name);
        String text = getIntent().getStringExtra("Winner");
        winner.setText(text);

        Button btn_back_to_menu = findViewById(R.id.result_to_menu);
        btn_back_to_menu.setOnClickListener(v-> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });

        Button ShareResult = findViewById(R.id.share_result);
        ShareResult.setOnClickListener(v-> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text + " одержал победу в игре Barabashka Game. ЭТО ОЧЕНЬ КРУТАЯ ИГРА, попробуй и ты");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

    }
}