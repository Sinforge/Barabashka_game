package ru.sinforge.barabashka_game.Activities;

import android.view.View;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import ru.sinforge.barabashka_game.Activities.YouTubeFragments.ViewPagerFragmentAdapter;
import ru.sinforge.barabashka_game.R;


public class GameRulesActivity extends AppCompatActivity {
    private final String[] titles = new String[] {"English", "Русский"};

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
        button.setOnClickListener(v-> {
            finish();
        });
        ViewPager2 viewPager2 = findViewById(R.id.pager1);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(this);
        viewPager2.setAdapter(viewPagerFragmentAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (((tab, position) -> tab.setText(titles[position])))).attach();



    }
}