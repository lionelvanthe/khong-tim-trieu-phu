package com.example.khongtimtrieuphu.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.khongtimtrieuphu.model.Question;

@Dao
public interface QuestionDAO {

    @Query("select * from question WHERE level= :level ORDER BY RANDOM() LIMIT 1")
    Question getAllQuestion(int level);

}
