package co.devhack.todoapp.presentation.presenter;

import java.util.List;

import co.devhack.todoapp.domain.model.Todo;

/**
 * Created by krlosf on 5/12/17.
 */

public interface TodoListContract {
    interface View {

        void refreshTodos();

    }

    interface UserActionsListener {

        void loadAll();

        List<Todo> getLstTodos();

    }
}
