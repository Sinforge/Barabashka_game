package ru.sinforge.barabashka_game.Database;

import androidx.annotation.ColorInt;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Result {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "winner")
    public String winner;


    @ColumnInfo(name = "score1")
    public int score1;

    @ColumnInfo(name = "score2")
    public int score2;

    @ColumnInfo(name = "date")
    public String date;

}
