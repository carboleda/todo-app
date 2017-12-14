package co.devhack.todoapp.domain.usecase.impl;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.domain.usecase.LocalDataUseCase;
import co.devhack.todoapp.domain.usecase.UserUseCase;
import co.devhack.todoapp.helpers.Callback;
import co.devhack.todoapp.helpers.Constants;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.repository.UserRepository;
import co.devhack.todoapp.repository.impl.UserFirebaseRepository;

/**
 * Created by krlosf on 2/12/17.
 */

public class UserUseCaseImpl implements UserUseCase {

    private UserRepository userRepository;
    private LocalDataUseCase localDataUseCase;

    public UserUseCaseImpl() {
        this.userRepository = new UserFirebaseRepository();
        this.localDataUseCase = new LocalDataUseCaseImpl();
    }

    @Override
    public void login(final String email, String password, final boolean remember, final Callback<User> callback) {
        userRepository.login(email, password, new Callback<User>() {
            @Override
            public void success(User user) {
                try {
                    if(user != null && remember) {
                        localDataUseCase.setData(Constants.SHARED_PREF_EMAIL, email);
                    }

                    localDataUseCase.setData(Constants.SHARED_PREF_IS_AUTH, true);
                    callback.success(user);
                } catch (Exception e) {
                    callback.error(e);
                }
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }

    @Override
    public void signUp(String fullName, String email, String password, final Callback<User> callback) {
        User user = new User(fullName, email, password);
        userRepository.signUp(user, new Callback<User>() {
            @Override
            public void success(User user) {
                callback.success(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }

    @Override
    public void recoveryPassword(String email, final Callback<Boolean> callback) {
        userRepository.recoveryPassword(email, new Callback<Boolean>() {
            @Override
            public void success(Boolean result) {
                callback.success(result);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }

    @Override
    public boolean isValidLoginAndPassword(String email, String password) {
        return !Utilities.isEmpty(email) && !Utilities.isEmpty(password);
    }
}
