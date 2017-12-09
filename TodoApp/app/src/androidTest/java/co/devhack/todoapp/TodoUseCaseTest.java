package co.devhack.todoapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import co.devhack.todoapp.database.AppDatabase;
import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.domain.usecase.TodoUseCase;
import co.devhack.todoapp.domain.usecase.impl.TodoUseCaseImpl;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by krlosf on 9/12/17.
 */
@RunWith(AndroidJUnit4.class)
public class TodoUseCaseTest {

    @Before
    public void initDb() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        AppDatabase.init(appContext);
    }

    @After
    public void closeDb() {
        AppDatabase.getInstance().close();
    }

    @Test
    public void insertTodo() {
        TodoUseCase todoUseCase = new TodoUseCaseImpl();
        todoUseCase.insert("Uno", new Date(), false, "http://aaa", 123, new Callback<Todo>() {
            @Override
            public void success(Todo result) {
                Assert.assertThat(result.getId(), Matchers.notNullValue());
            }

            @Override
            public void error(Exception error) {
                Assert.assertThat(error, Matchers.notNullValue());
            }
        });
    }

}
