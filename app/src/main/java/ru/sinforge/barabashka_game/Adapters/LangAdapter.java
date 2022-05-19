package ru.sinforge.barabashka_game.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import ru.sinforge.barabashka_game.R;
import ru.sinforge.barabashka_game.Services.Languages;

import java.util.ArrayList;

public class LangAdapter extends ArrayAdapter<Languages> {
    private Context context;
    ArrayList<Languages> data = null;

    public LangAdapter(Context context, int resource, ArrayList<Languages> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
    }

    /*@Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) { // этот код выполняется, когда вы нажимаете на спиннер
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.row_lang, parent, false);
        }

        Languages item = data.get(position);
        ImageView myFlag = row.findViewById(R.id.flag);
        myFlag.setImageResource(item.getImg());
        return row;
    }*/

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lang,parent,false);
        ImageView flag = row.findViewById(R.id.flag);
        flag.setImageResource(data.get(position).getImg());
        return row;
    }
}


