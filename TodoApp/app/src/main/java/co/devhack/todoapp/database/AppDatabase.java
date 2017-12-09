package co.devhack.todoapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.repository.impl.TodoLocalRepository;

/**
 * Created by krlosf on 5/12/17.
 */
@Database(entities = {Todo.class}, version = 1)
@TypeConverters({TypeTransmogrifiers.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase db;

    public static void init(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "todo-app").build();
            /*db = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                    AppDatabase.class).build();*/
        }
    }

    public static AppDatabase getInstance() {
        return db;
    }

    public abstract TodoLocalRepository.TodoDao todoDao();
}
