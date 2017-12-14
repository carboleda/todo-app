package co.devhack.todoapp.presentation.presenter;

/**
 * Created by krlosf on 30/11/17.
 */

public interface LoginContract {

    interface View {
        void goToSignUpFragment();

        void goToMainActivity();

        void showMessageError(Exception error);

        void showProgress();

        void hideProgress();

        void showRememberedUser(String email);
    }

    interface UserActionsListener {
        void onLogin(String email, String password, boolean remember);

        void configure();
    }

}
