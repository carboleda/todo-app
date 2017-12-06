package co.devhack.todoapp;

import android.app.Application;

import co.devhack.todoapp.database.AppDatabase;

/**
 * Created by krlosf on 5/12/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase.init(getApplicationContext());
    }
}
