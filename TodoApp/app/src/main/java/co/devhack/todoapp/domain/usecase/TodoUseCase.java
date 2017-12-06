package co.devhack.todoapp.domain.usecase;

import java.util.Date;
import java.util.List;

import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by krlosf on 5/12/17.
 */

public interface TodoUseCase {

    void insert(String description, Date finishDate, Boolean finished, String image, int color,
                Callback<Todo> callback);

    void update(Todo todo, Callback<Todo> callback);

    void delete(Todo todo, Callback<Boolean> callback);

    void getAll(Callback<List<Todo>> callback);

}
