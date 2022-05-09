package ru.sinforge.barabashka_game.GameComponents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.sinforge.barabashka_game.Activities.MenuActivity;
import ru.sinforge.barabashka_game.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PauseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PauseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PauseFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PauseFragment newInstance(String param1, String param2) {
        PauseFragment fragment = new PauseFragment();
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
        View inflate = inflater.inflate(R.layout.fragment_pause, container, false);
        Button btn_resume = inflate.findViewById(R.id.resume);
        Button btn_exit = inflate.findViewById(R.id.exit_to_menu);
        btn_exit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            startActivity(intent);
        });
        btn_resume.setOnClickListener(view -> {
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();


        }
        );

        return inflate;
    }

}