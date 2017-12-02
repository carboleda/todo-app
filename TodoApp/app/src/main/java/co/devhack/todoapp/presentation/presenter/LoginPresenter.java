package co.devhack.todoapp.presentation.presenter;

/**
 * Created by krlosf on 30/11/17.
 */

public class LoginPresenter implements LoginContract.UserActionsListener {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onLogin(String email, String password, boolean remember) {

    }

}
