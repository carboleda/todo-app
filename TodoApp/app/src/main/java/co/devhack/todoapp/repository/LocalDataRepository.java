package co.devhack.todoapp.repository;

/**
 * Created by krlosf on 12/12/17.
 */

public interface LocalDataRepository {

    <T> boolean setData(String key, T value) throws Exception;
    <T> T getData(String key, Class<T> type) throws Exception;
    void clearAllData() throws Exception;

}
