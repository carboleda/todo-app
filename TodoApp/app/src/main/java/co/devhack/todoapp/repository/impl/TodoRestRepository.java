package co.devhack.todoapp.repository.impl;

import java.util.List;

import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.helpers.RetrofitSingleton;
import co.devhack.todoapp.repository.TodoRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by krlosf on 9/12/17.
 */

public class TodoRestRepository implements TodoRepository {

    public interface TodoService {
        @PUT("todos/{id}.json")
        Call<Todo> insert(@Path("id") Integer id, @Body Todo todo);

        @GET("todos.json")
        Call<List<Todo>> getAll();
    }

    @Override
    public Long insert(Todo todo) throws Exception {
        List<Todo> lstTodos = getAll();
        int size = lstTodos.size();
        todo.setId(size);

        Retrofit retrofit = RetrofitSingleton.getInstance();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<Todo> call = todoService.insert(todo.getId(), todo);

        Response<Todo> response = call.execute();

        return Long.valueOf(todo.getId());
    }

    @Override
    public void update(Todo todo) {
        //TODO IMPLEMENTAR
    }

    @Override
    public void delete(Todo todo) {
        //TODO IMPLEMENTAR
    }

    @Override
    public List<Todo> getAll() throws Exception {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<List<Todo>> call = todoService.getAll();

        Response<List<Todo>> response = call.execute();

        return response.body();
    }
}
