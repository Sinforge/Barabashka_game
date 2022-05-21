package ru.sinforge.barabashka_game.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.sinforge.barabashka_game.Adapters.ResultAdapter;
import ru.sinforge.barabashka_game.Database.AppDatabase;
import ru.sinforge.barabashka_game.Database.Result;
import ru.sinforge.barabashka_game.R;

import java.util.List;

public class GamesHistoryActivity extends AppCompatActivity{
    private ResultAdapter myAdapter;
    private Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_history);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        initRecyclerView();
        Button button = findViewById(R.id.back1);
        button.setOnClickListener(v-> {
            finish();
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration1);
        myAdapter = new ResultAdapter(generateData());
        recyclerView.setAdapter(myAdapter);
        clear = findViewById(R.id.clear_history);
        clear.setOnClickListener(v->{
            AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
            db.resultDAO().deleteAllResult(db.resultDAO().getAllResults());
            myAdapter.notifyDataSetChanged();
            Toast.makeText(this, R.string.clear_h, Toast.LENGTH_SHORT).show();
        });
    }



    public List<Result> generateData() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        return db.resultDAO().getAllResults();
    }
}