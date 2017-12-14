package co.devhack.todoapp.domain.usecase.impl;

import co.devhack.todoapp.domain.usecase.LocalDataUseCase;
import co.devhack.todoapp.repository.LocalDataRepository;
import co.devhack.todoapp.repository.impl.LocalDataRepositoryImpl;

/**
 * Created by krlosf on 12/12/17.
 */

public class LocalDataUseCaseImpl implements LocalDataUseCase {

    private LocalDataRepository localDataRepository;

    public LocalDataUseCaseImpl() {
        this.localDataRepository = new LocalDataRepositoryImpl();
    }

    @Override
    public <T> boolean setData(String key, T value) throws Exception {
        return localDataRepository.setData(key, value);
    }

    @Override
    public <T> T getData(String key, Class<T> type) throws Exception {
        return localDataRepository.getData(key, type);
    }

    @Override
    public void clearAllData() throws Exception {
        localDataRepository.clearAllData();
    }
}
