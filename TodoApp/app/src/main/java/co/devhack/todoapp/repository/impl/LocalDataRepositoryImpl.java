package co.devhack.todoapp.repository.impl;

import android.content.SharedPreferences;

import java.util.Map;

import co.devhack.todoapp.helpers.SharedPreferencesUtil;
import co.devhack.todoapp.repository.LocalDataRepository;

/**
 * Created by krlosf on 12/12/17.
 */

public class LocalDataRepositoryImpl implements LocalDataRepository {

    @Override
    public <T> boolean setData(String key, T value) throws Exception {
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(value == null) {
            editor.remove(key);
        } else if(value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if(value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if(value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if(value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if(value instanceof String) {
            editor.putString(key, (String) value);
        }

        return editor.commit();
    }

    @Override
    public <T> T getData(String key, Class<T> type) throws Exception {
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
        Map<String, Object> preferences = (Map<String, Object>) sharedPreferences.getAll();
        Object value = preferences.get(key);

        try {
            if(value != null) {
                return type.cast(value);
            }

            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void clearAllData() throws Exception {
        try {
            SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
            sharedPreferences.edit().clear().commit();
        } catch (Exception e) {
            throw e;
        }
    }
}
