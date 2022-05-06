package ru.sinforge.barabashka_game.menu_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ru.sinforge.barabashka_game.Activities.Activity_for_play;
import ru.sinforge.barabashka_game.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
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
        View menu = inflater.inflate(R.layout.fragment_menu, container, false);
        Button btn_settings = menu.findViewById(R.id.button_settings);
        btn_settings.setOnClickListener(this);
        Button btn_play = menu.findViewById(R.id.button_play);
        btn_play.setOnClickListener(this);
        Button btn_exit = menu.findViewById(R.id.button_exit);
        btn_exit.setOnClickListener(this);
        Button btn_rules = menu.findViewById(R.id.button_rules);
        btn_rules.setOnClickListener(this);


        return menu;
    }


    private Fragment menu_fragment;
    private String operation;


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play:
                operation = "go to new activity";
                Intent intent = new Intent(getContext(), Activity_for_play.class);
                startActivity(intent);
                break;
            case R.id.button_rules:
                operation = "go_to_rules";
                menu_fragment = new GameRules();
                break;
            case R.id.button_settings:
                menu_fragment = new Settings();
                operation = "go_to_setting";
                break;
        }
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.start_layout, menu_fragment);
        trans.addToBackStack(operation);
        trans.commit();
    }
}