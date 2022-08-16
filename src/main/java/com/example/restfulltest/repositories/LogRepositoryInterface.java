package com.example.restfulltest.repositories;

import java.util.List;

public interface LogRepositoryInterface<T> {
        public Integer save(T t) throws Exception;
        public T search(Integer id);
        public void delete(T t);
        public List<T> getAll();
        public T update(T t);
}

