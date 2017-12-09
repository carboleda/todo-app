package co.devhack.todoapp.presentation.presenter;

import java.util.Date;

/**
 * Created by krlosf on 8/12/17.
 */

public interface AddTodoContract {
    interface View {
        void goToTodoListFragment();

        void showMessageError(Exception error);
    }

    interface UserActionsListener {
        void onSave(String description, boolean finished, Date finishDate, String image);
    }
}

