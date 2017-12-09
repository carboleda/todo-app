package co.devhack.todoapp.presentation.presenter;

/**
 * Created by krlosf on 8/12/17.
 */

import java.util.Date;

import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.domain.usecase.TodoUseCase;
import co.devhack.todoapp.domain.usecase.impl.TodoUseCaseImpl;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by krlosf on 3/12/17.
 */

public class AddTodoPresenter implements AddTodoContract.UserActionsListener {

    private AddTodoContract.View view;
    private TodoUseCase todoUseCase;

    public AddTodoPresenter(AddTodoContract.View view) {
        this.view = view;
        this.todoUseCase =  new TodoUseCaseImpl();
    }

    @Override
    public void onSave(String description, boolean finished, Date finishDate, String image) {
        todoUseCase.insert(description, finishDate, false, image, 0, new Callback<Todo>() {
            @Override
            public void success(Todo result) {
                view.goToTodoListFragment();
            }

            @Override
            public void error(Exception error) {
                view.showMessageError(error);
            }
        });
    }
}
