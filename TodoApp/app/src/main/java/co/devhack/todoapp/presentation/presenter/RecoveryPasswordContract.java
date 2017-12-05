package co.devhack.todoapp.presentation.presenter;

/**
 * Created by krlosf on 4/12/17.
 */

public interface RecoveryPasswordContract {

    interface View {
        void showErrorMessage(Exception error);

        void showSuccessMessage();
    }

    interface UserActionsListener {
        void onRecovery(String email);
    }

}
