package ru.sinforge.barabashka_game.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.R;
import ru.sinforge.barabashka_game.Services.Languages;

import java.util.ArrayList;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity {
    private boolean stop_music = false;
    private ImageView sound;
    private MediaPlayer mediaPlayer;
    private final Activity context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        makeFullScreen();


        mediaPlayer = MediaPlayer.create(this, R.raw.menu);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        sound = findViewById(R.id.sound);
        sound.setImageResource(R.drawable.sound);
        sound.setOnClickListener(this::onClick);



        Spinner spinner = findViewById(R.id.lang_spinner);

        ArrayList<Languages> languages = new ArrayList<>();
        languages.add(new Languages(R.drawable.world, "world"));
        languages.add(new Languages(R.drawable.ru, "ru"));
        languages.add(new Languages( R.drawable.en, "en"));


        MyCustomAdapter langAdapter = new MyCustomAdapter(this, R.layout.row_lang, languages);
        spinner.setAdapter(langAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            {makeFullScreen();}
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem() != null) {
                    Languages flag = (Languages) spinner.getSelectedItem();
                    if(!flag.getName().equals("world")) {
                        setLang(flag.getName());
                        reloadScreen(flag.getName());
                        Toast.makeText(getApplicationContext(), (R.string.set_new_lang), Toast.LENGTH_SHORT).show();
                        makeFullScreen();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                makeFullScreen();
            }
        });


        Button btn_play = findViewById(R.id.button_play);
        btn_play.setOnClickListener(this::onClick);
        Button btn_exit = findViewById(R.id.button_exit);
        btn_exit.setOnClickListener(this::onClick);
        Button btn_rules = findViewById(R.id.button_rules);
        btn_rules.setOnClickListener(this::onClick);
        Button btn_history = findViewById(R.id.history);
        btn_history.setOnClickListener(this::onClick);

    }


    private void reloadScreen(String Lang) {
        Button play = findViewById(R.id.button_play);
        Button rules = findViewById(R.id.button_rules);
        Button history = findViewById(R.id.history);
        Button exit = findViewById(R.id.button_exit);
        if (Lang.equals("en")) {
            play.setText("PLAY");
            rules.setText("RULES");
            history.setText("HISTORY");
            exit.setText("EXIT");

        }
        else {
            play.setText("ИГРАТЬ");
            rules.setText("ПРАВИЛА");
            history.setText("ИСТОРИЯ");
            exit.setText("ВЫХОД");
        }
    }


    private void setLang(String Lang) {
        Locale locale = new Locale(Lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    private void makeFullScreen() {
        context.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.sound:
                if(stop_music) {
                    mediaPlayer.start();
                    sound.setImageResource(R.drawable.sound);
                    stop_music= false;
                }
                else {
                    mediaPlayer.pause();
                    sound.setImageResource(R.drawable.mute1);
                    stop_music = true;
                }

                break;
            case R.id.history:
                intent = new Intent(this, GamesHistoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.button_play:
                mediaPlayer.stop();
                intent = new Intent(this, GameMode.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.button_rules:
                intent = new Intent(this, GameRulesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.button_exit:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.exit)
                        .setMessage(R.string.dialog_exit)
                        .setPositiveButton(R.string.yes, (dialog, which) -> {
                            this.finishAndRemoveTask();
                            mediaPlayer.stop();
                        })
                        .setNegativeButton(R.string.no, (dialog, which) -> {
                            makeFullScreen();
                        }  )
                        .show();
                break;
        }

    }
    public class MyCustomAdapter extends ArrayAdapter<Languages> {

        private ArrayList<Languages> objects;
        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Languages> objects) {
            super(context, textViewResourceId, objects);
            this.objects = objects;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row_lang, parent, false);

            ImageView icon = row.findViewById(R.id.flag);

            icon.setImageResource(objects.get(position).getImg());
            return row;
        }
    }
}