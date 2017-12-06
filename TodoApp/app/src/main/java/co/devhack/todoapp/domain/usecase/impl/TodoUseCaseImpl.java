package co.devhack.todoapp.domain.usecase.impl;

import java.util.Date;
import java.util.List;

import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.domain.usecase.TodoUseCase;
import co.devhack.todoapp.helpers.Callback;
import co.devhack.todoapp.helpers.ThreadExecutor;
import co.devhack.todoapp.repository.TodoRepository;
import co.devhack.todoapp.repository.impl.TodoLocalRepository;

/**
 * Created by krlosf on 5/12/17.
 */

public class TodoUseCaseImpl implements TodoUseCase {

    private TodoRepository todoRepository;

    public TodoUseCaseImpl() {
        this.todoRepository = new TodoLocalRepository();
    }

    @Override
    public void insert(final String description, final Date finishDate, final Boolean finished,
                       final String image, final int color, final Callback<Todo> callback) {
        new ThreadExecutor<Todo>(new ThreadExecutor.Task<Todo>() {
            @Override
            public Todo execute() throws Exception {
                Todo todo = new Todo(description, finishDate, finished, image, color);
                todoRepository.insert(todo);
                return todo;
            }

            @Override
            public void finish(Exception error, Todo result) {
                if(error != null) {
                    callback.error(error);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void update(Todo todo, Callback<Todo> callback) {
        //TODO IMPLEMENTAR
    }

    @Override
    public void delete(Todo todo, Callback<Boolean> callback) {
        //TODO IMPLEMENTAR
    }

    @Override
    public void getAll(final Callback<List<Todo>> callback) {
        new ThreadExecutor<List<Todo>>(new ThreadExecutor.Task<List<Todo>>() {
            @Override
            public List<Todo> execute() throws Exception {
                return todoRepository.getAll();
            }

            @Override
            public void finish(Exception error, List<Todo> result) {
                if(error != null) {
                    callback.error(error);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }
}
