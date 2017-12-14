package co.devhack.todoapp.repository.impl;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.helpers.RetrofitSingleton;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.repository.TodoRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by krlosf on 9/12/17.
 */

public class TodoRestRepository implements TodoRepository {

    public interface TodoService {
        @PUT("todos/{id}.json")
        Call<Todo> insert(@Path("id") Integer id, @Body Todo todo);

        @PATCH("todos/{id}.json")
        Call<Todo> update(@Path("id") Integer id, @Body Todo todo);

        @DELETE("todos/{id}.json")
        Call<Todo> delete(@Path("id") Integer id);

        @GET("todos.json")
        Call<List<Todo>> getAll();
    }

    @Override
    public Long insert(final Todo todo) throws Exception {
        List<Todo> lstTodos = getAll();
        int size = 0;
        if(lstTodos != null) {
            size = lstTodos.size();
        }
        todo.setId(size);
        String imageUrl = uploadImage(todo.getId(), todo.getImage());
        todo.setImage(imageUrl);

        Retrofit retrofit = RetrofitSingleton.getInstance();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<Todo> call = todoService.insert(todo.getId(), todo);

        Response<Todo> response = call.execute();

        return Long.valueOf(todo.getId());
    }

    @Override
    public void update(Todo todo) throws Exception {
        String imageUrl = uploadImage(todo.getId(), todo.getImage());
        todo.setImage(imageUrl);

        Retrofit retrofit = RetrofitSingleton.getInstance();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<Todo> call = todoService.update(todo.getId(), todo);

        Response<Todo> response = call.execute();
    }

    @Override
    public void delete(Todo todo) throws Exception {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<Todo> call = todoService.delete(todo.getId());

        Response<Todo> response = call.execute();
    }

    @Override
    public List<Todo> getAll() throws Exception {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<List<Todo>> call = todoService.getAll();

        Response<List<Todo>> response = call.execute();

        return response.body();
    }

    private String uploadImage(Integer todoId, String image) throws FileNotFoundException {
        if(!Utilities.isEmpty(image)) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

            File file = new File(image);
            FileInputStream fis = new FileInputStream(file);

            // Create a reference to "mountains.jpg"
            StorageReference mountainsRef = storageRef.child(String.format("todos_images/%d.jpg", todoId));
            UploadTask uploadTask = mountainsRef.putStream(fis);
            UploadTask.TaskSnapshot taskSnapshot = null;
            try {
                while (!uploadTask.isComplete()) {
                    Thread.sleep(100);
                }
                taskSnapshot = uploadTask.getSnapshot();
                if (taskSnapshot != null && taskSnapshot.getDownloadUrl() != null) {
                    return taskSnapshot.getDownloadUrl().toString();
                }
            } catch (InterruptedException e) {
                return null;
            }
        }

        return image;
    }
}
