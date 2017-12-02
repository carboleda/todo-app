package co.devhack.todoapp.helpers;

import co.devhack.todoapp.domain.model.User;

/**
 * Created by krlosf on 2/12/17.
 */

public interface Callback<T> {
    void success(T result);

    void error(Exception error);
}
