package ru.sinforge.barabashka_game.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.sinforge.barabashka_game.Adapters.LangAdapter;
import ru.sinforge.barabashka_game.R;
import ru.sinforge.barabashka_game.Services.Languages;

import java.util.ArrayList;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        Spinner spinner = findViewById(R.id.lang_spinner);

        ArrayList<Languages> languages = new ArrayList<>();
        languages.add(new Languages(R.drawable.ru, "Russian"));
        languages.add(new Languages( R.drawable.en, "English"));

        MyCustomAdapter langAdapter = new MyCustomAdapter(this, R.layout.row_lang, languages);
        spinner.setAdapter(langAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem() != null) {
                    Languages flag = (Languages) spinner.getSelectedItem();
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);

                    Configuration config = getApplicationContext().getResources().getConfiguration();
                    config.setLocale(locale);
                    getApplicationContext().createConfigurationContext(config);

                    getApplicationContext().getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.history:
                intent = new Intent(this, GamesHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.button_play:
                intent = new Intent(this, GameMode.class);
                startActivity(intent);
                break;
            case R.id.button_rules:
                intent = new Intent(this, GameRulesActivity.class);
                startActivity(intent);
                break;
            case R.id.button_exit:
                this.finish();
                System.exit(0);
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