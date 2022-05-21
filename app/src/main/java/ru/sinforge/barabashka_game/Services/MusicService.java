package ru.sinforge.barabashka_game.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import ru.sinforge.barabashka_game.R;

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        mediaPlayer = MediaPlayer.create(this, R.raw.bg);
        mediaPlayer.setLooping(true); // зацикливаем
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        mediaPlayer.start();
    }
}