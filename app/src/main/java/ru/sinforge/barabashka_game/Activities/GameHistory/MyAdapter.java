package ru.sinforge.barabashka_game.Activities.GameHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.sinforge.barabashka_game.Database.Result;
import ru.sinforge.barabashka_game.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewRow> {

    private Context context;
    private List<Result> arrayList;

    public MyAdapter(List<Result> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    // lombok

    @NonNull
    @Override
    public ViewRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_row,parent,false);
        return new ViewRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRow holder, int position) {
        holder.id.setText(arrayList.get(position).uid + "");
        holder.Winner.setText(arrayList.get(position).winner);
        holder.Score1.setText(arrayList.get(position).score1 + "");
        holder.Score2.setText(arrayList.get(position).score2 + "");
        holder.Date.setText(arrayList.get(position).date);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewRow extends RecyclerView.ViewHolder {

        TextView id;
        TextView Winner;
        TextView Score1;
        TextView Score2;
        TextView Date;

        public ViewRow(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.identif);
            Winner = itemView.findViewById(R.id.Winner);
            Score1 = itemView.findViewById(R.id.Score1);
            Score2 = itemView.findViewById(R.id.Score2);
            Date = itemView.findViewById(R.id.date);

        }
    }
}