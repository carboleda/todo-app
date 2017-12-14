package co.devhack.todoapp.presentation.presenter;

/**
 * Created by krlosf on 12/12/17.
 */

public interface MainContarct {

    interface View {

        void goToAuthActivity();

        void showMessageError(Exception e);
    }

    interface UserActionsListener {

        void exit();
    }

}
