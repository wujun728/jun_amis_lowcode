package com.jqp.admin.common.service;

public interface CacheService<T>{
    T get(String key);
    void invalid(String key);
    void clear();
}
