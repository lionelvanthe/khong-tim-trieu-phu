package com.example.khongtimtrieuphu.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.khongtimtrieuphu.model.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionRoomDatabase extends RoomDatabase {

    public abstract QuestionDAO questionDAO();
    private static QuestionRoomDatabase INSTANCE;

    public static QuestionRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (QuestionRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuestionRoomDatabase.class,
                            "Question_database")
                           .createFromAsset("database/Question.db")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
