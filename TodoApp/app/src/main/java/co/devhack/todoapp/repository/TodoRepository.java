package co.devhack.todoapp.repository;

import java.io.IOException;
import java.util.List;

import co.devhack.todoapp.domain.model.Todo;

/**
 * Created by krlosf on 5/12/17.
 */

public interface TodoRepository {

    Long insert(Todo todo) throws Exception;

    void update(Todo todo);

    void delete(Todo todo);

    List<Todo> getAll() throws Exception;

}
