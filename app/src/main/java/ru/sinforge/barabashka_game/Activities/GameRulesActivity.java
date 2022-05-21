package ru.sinforge.barabashka_game.Activities;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import org.jetbrains.annotations.NotNull;
import ru.sinforge.barabashka_game.R;

import java.util.Locale;

public class GameRulesActivity extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    private boolean lang = Locale.getDefault().getLanguage().equals("ru");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Button button = findViewById(R.id.back1);
        youTubePlayerView = findViewById(R.id.youtube);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onApiChange(@NotNull YouTubePlayer youTubePlayer) {
                super.onApiChange(youTubePlayer);
                String id="";
                if (lang) {
                    id = "QeTAMyoO-k0";
                }
                else {
                    id = "bkAuoFPCe9c";
                }
                youTubePlayer.loadVideo(id, 0);
            }
        });


        button.setOnClickListener(v-> {
            finish();
        });
    }
}