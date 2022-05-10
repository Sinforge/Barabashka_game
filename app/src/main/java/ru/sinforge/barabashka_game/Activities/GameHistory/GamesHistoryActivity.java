package ru.sinforge.barabashka_game.Activities.GameHistory;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.sinforge.barabashka_game.Database.AppDatabase;
import ru.sinforge.barabashka_game.Database.Result;
import ru.sinforge.barabashka_game.R;

import java.util.List;

public class GamesHistoryActivity extends AppCompatActivity {
    private MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_history);
        initRecyclerView();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        myAdapter = new MyAdapter(generateData());
        recyclerView.setAdapter(myAdapter);
    }



    public List generateData() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Result> results = db.resultDAO().getAllResults();
        return results;
    }
}