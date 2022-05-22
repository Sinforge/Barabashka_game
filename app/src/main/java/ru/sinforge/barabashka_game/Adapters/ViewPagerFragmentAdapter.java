package ru.sinforge.barabashka_game.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.jetbrains.annotations.NotNull;
import ru.sinforge.barabashka_game.Activities.YouTubeFragments.Player1;
import ru.sinforge.barabashka_game.Activities.YouTubeFragments.Player2;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private String[] titles = new String[] {"Russian", "English"};
    public ViewPagerFragmentAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Player1();
            case 1:
                return new Player2();
        }
        return new Player1();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
