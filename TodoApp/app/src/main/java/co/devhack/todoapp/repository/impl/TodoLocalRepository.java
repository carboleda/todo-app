package co.devhack.todoapp.repository.impl;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.devhack.todoapp.database.AppDatabase;
import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.repository.TodoRepository;

/**
 * Created by krlosf on 5/12/17.
 */

public class TodoLocalRepository implements TodoRepository {

    @Dao
    public interface TodoDao {
        @Insert
        void insert(Todo todo);

        @Update
        void update(Todo todo);

        @Delete
        void delete(Todo todo);

        @Query("select * from todo")
        List<Todo> getAll();
    }

    @Override
    public void insert(Todo todo) {
        AppDatabase db = AppDatabase.getInstance();
        TodoDao todoDao = db.todoDao();
        todoDao.insert(todo);
    }

    @Override
    public void update(Todo todo) {
        //TODO IMPLEMENTAR
    }

    @Override
    public void delete(Todo todo) {
        //TODO IMPLEMETAR
    }

    @Override
    public List<Todo> getAll() {
        AppDatabase db = AppDatabase.getInstance();
        TodoDao todoDao = db.todoDao();
        return todoDao.getAll();
    }
}
