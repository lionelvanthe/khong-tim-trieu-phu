package com.example.khongtimtrieuphu;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.khongtimtrieuphu.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Preference {

    private static final String PREFERENCE_NAME = "user";
    private final SharedPreferences sharedPreferences;
    private List<User> users;


    public Preference(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }



    public void savePref(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(users);

        editor.putString("list_user", json);

        editor.apply();
    }

    public List<User> getPref(){
        Gson gson = new Gson();


        String json = sharedPreferences.getString("list_user", null);

        Type type = new TypeToken<ArrayList<User>>() {}.getType();

        users = gson.fromJson(json, type);

        if (users == null) {
            users =  new ArrayList<>();
        }
        return users;
    }

    public List<User> getUsers() {
        return users;
    }
}
