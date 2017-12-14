package co.devhack.todoapp.presentation.presenter;

import co.devhack.todoapp.domain.usecase.LocalDataUseCase;
import co.devhack.todoapp.domain.usecase.impl.LocalDataUseCaseImpl;
import co.devhack.todoapp.helpers.Constants;

/**
 * Created by krlosf on 30/11/17.
 */

public class AuthPresenter implements AuthContract.UserActionsLister {

    private AuthContract.View view;
    private LocalDataUseCase localDataUseCase;

    public AuthPresenter(AuthContract.View view) {
        this.view = view;
        this.localDataUseCase = new LocalDataUseCaseImpl();
    }

    @Override
    public void goToFirstFragment() {
        try {
            //Si el usuario no esta autenticado
            Boolean isAuth = localDataUseCase.getData(Constants.SHARED_PREF_IS_AUTH, Boolean.class);
            if (isAuth == null || !isAuth) {
                view.goToLoginFragment();
            } else {
                view.goMainActivity();
            }
        } catch (Exception e) {

        }
    }
}
