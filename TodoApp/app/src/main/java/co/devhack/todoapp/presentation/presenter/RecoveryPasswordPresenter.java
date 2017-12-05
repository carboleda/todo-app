package co.devhack.todoapp.presentation.presenter;

import co.devhack.todoapp.domain.usecase.UserUseCase;
import co.devhack.todoapp.domain.usecase.impl.UserUseCaseImpl;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by krlosf on 4/12/17.
 */

public class RecoveryPasswordPresenter implements RecoveryPasswordContract.UserActionsListener {

    private RecoveryPasswordContract.View view;
    private UserUseCase userUseCase;

    public RecoveryPasswordPresenter(RecoveryPasswordContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onRecovery(String email) {
        userUseCase.recoveryPassword(email, new Callback<Boolean>() {
            @Override
            public void success(Boolean result) {
                view.showSuccessMessage();
            }

            @Override
            public void error(Exception error) {
                view.showErrorMessage(error);
            }
        });
    }
}
