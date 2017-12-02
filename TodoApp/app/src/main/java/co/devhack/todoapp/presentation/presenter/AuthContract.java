package co.devhack.todoapp.presentation.presenter;

/**
 * Created by krlosf on 30/11/17.
 */

public interface AuthContract {

    //Interface que implementara el Fragment o Activity
    interface View {
        void goToLoginFragment();

        void goMainActivity();
    }

    //Interface que implementara el presenter
    interface UserActionsLister {
        void goToFirstFragment();
    }
}
