package com.learn.cache.redis.Repository;

import com.learn.cache.redis.model.User;

import java.util.Map;

public interface IUserRepository {

    void save(User user);

    Map<String,User> findAll();

    User findById(String id);

    void updateUser(User user);

    void delete(String id);
}
