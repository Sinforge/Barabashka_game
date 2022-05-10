package ru.sinforge.barabashka_game.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ResultDAO {

    @Query("SELECT * FROM Result")
    List<Result> getAllResults();

    @Insert
    void insertResult(Result...results);

    @Delete
    void deleteResult(Result result);

}
