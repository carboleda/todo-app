package co.devhack.todoapp.presentation.presenter;

import co.devhack.todoapp.domain.usecase.LocalDataUseCase;
import co.devhack.todoapp.domain.usecase.impl.LocalDataUseCaseImpl;
import co.devhack.todoapp.helpers.Constants;

/**
 * Created by krlosf on 12/12/17.
 */

public class MainPresenter implements MainContarct.UserActionsListener {

    private MainContarct.View view;
    private LocalDataUseCase localDataUseCase;

    public MainPresenter(MainContarct.View view) {
        this.view = view;
        this.localDataUseCase = new LocalDataUseCaseImpl();
    }

    @Override
    public void exit() {
        try {
            localDataUseCase.setData(Constants.SHARED_PREF_IS_AUTH, false);
            view.goToAuthActivity();
        } catch (Exception e) {
            view.showMessageError(e);
        }
    }
}
