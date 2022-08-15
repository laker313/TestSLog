package com.example.restfulltest.repositories;

public interface LogRepositoryInterface<T> {
        public Integer save(T t) throws Exception;
        public T search(Integer id);
        public T delete(Integer id);
}

