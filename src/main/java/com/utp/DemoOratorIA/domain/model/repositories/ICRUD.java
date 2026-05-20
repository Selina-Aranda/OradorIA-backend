package com.utp.DemoOratorIA.domain.model.repositories;

import java.util.List;

public interface ICRUD <T> {
    T save(T t);
    T findById(Integer id);
    T update(T t);
    List<T> list();
    void delete(Integer id);
    
}
