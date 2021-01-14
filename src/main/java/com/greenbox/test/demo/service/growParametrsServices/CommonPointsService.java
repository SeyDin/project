package com.greenbox.test.demo.service.growParametrsServices;

import java.util.Set;

public interface CommonPointsService<T>{
    void create(T t);
    Set<T> readAll();
    T read(long id);
    void update(T t, Long id);
    void delete(long id);
}
