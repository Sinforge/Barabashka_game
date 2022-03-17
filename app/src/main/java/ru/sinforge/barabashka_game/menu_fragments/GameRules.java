package ru.sinforge.barabashka_game.menu_fragments;

import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ru.sinforge.barabashka_game.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameRules#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameRules extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameRules() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameRules.
     */
    // TODO: Rename and change types and number of parameters
    public static GameRules newInstance(String param1, String param2) {
        GameRules fragment = new GameRules();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View game_rules = inflater.inflate(R.layout.fragment_game_rules, container, false);
        Button btn_close = game_rules.findViewById(R.id.button_close2);
        btn_close.setOnClickListener(v -> {
            Fragment menu_fragment = new Menu();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.replace(R.id.start_layout, menu_fragment);
            trans.addToBackStack("back_to_menu");
            trans.commit();
        });
        return game_rules;
    }
}