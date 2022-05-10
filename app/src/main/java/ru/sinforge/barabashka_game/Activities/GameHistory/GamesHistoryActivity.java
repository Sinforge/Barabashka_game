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
    private int ID[] = {1, 2, 3, 4, 5};
    private String winners[] = {"anton", "vlad", "oleg", "criptic","aloha"};
    private int Scores1[] = {10, 15, 15, 5, 2};
    private int Scores2[] = {15, 4, 3, 2, 6};
    private String dates[] = {"03.04.2022", "03.04.2022", "03.04.2022", "03.04.2022", "03.04.2022"};
    MyAdapter myAdapter;
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


        myAdapter = new MyAdapter(generateData(), getApplicationContext());
        recyclerView.setAdapter(myAdapter);
    }

    public List generateData() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Result> results = db.resultDAO().getAllResults();
        return results;
    }
}