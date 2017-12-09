package co.devhack.todoapp;

import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import co.devhack.todoapp.database.AppDatabase;
import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.domain.usecase.TodoUseCase;
import co.devhack.todoapp.domain.usecase.UserUseCase;
import co.devhack.todoapp.domain.usecase.impl.TodoUseCaseImpl;
import co.devhack.todoapp.domain.usecase.impl.UserUseCaseImpl;
import co.devhack.todoapp.helpers.Callback;
import co.devhack.todoapp.repository.impl.TodoLocalRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by krlosf on 8/12/17.
 */
@RunWith(android.support.test.runner.AndroidJUnit4.class)
public class UserUseCaseUnitTest {

    @Before
    public void initDb() throws Exception {
        AppDatabase.init(InstrumentationRegistry.getContext());
    }

    @After
    public void closeDb() {
        AppDatabase.getInstance().close();
    }

    @Test
    public void insertTodo() {
        TodoUseCase todoUseCase = new TodoUseCaseImpl();
        todoUseCase.insert("Uno", new Date(), false, "", 123, new Callback<Todo>() {
            @Override
            public void success(Todo result) {
                assertThat(result.getId(), notNullValue());
            }

            @Override
            public void error(Exception error) {
                assertThat(error, notNullValue());
            }
        });
    }

    @Test
    public void updateTodo() {
        TodoUseCase todoUseCase = new TodoUseCaseImpl();
        todoUseCase.insert("Uno", new Date(), false, "", 123, new Callback<Todo>() {
            @Override
            public void success(Todo result) {}

            @Override
            public void error(Exception error) {}
        });
        todoUseCase.getAll(new Callback<List<Todo>>() {
            @Override
            public void success(List<Todo> result) {
                assertFalse(result.isEmpty());
                performUpdate(result.get(0));
            }

            @Override
            public void error(Exception error) {
                assertThat(error, notNullValue());
            }
        });
    }

    public void performUpdate(final Todo todo) {
        todo.setColor(456);
        TodoUseCase todoUseCase = new TodoUseCaseImpl();
        todoUseCase.update(todo, new Callback<Todo>() {
            @Override
            public void success(Todo result) {
                assertThat(result.getColor(), is(todo.getColor()));
            }

            @Override
            public void error(Exception error) {
                assertThat(error, notNullValue());
            }
        });
    }
}
