package com.learn.cache.redis.Repository;

import com.learn.cache.redis.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private RedisTemplate<String, User> redisTemplate;

    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String,User> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
      hashOperations.put("USER:ID",user.getId(),user);
    }

    @Override
    public Map<String,User> findAll() {
        return hashOperations.entries("USER:ID");
    }

    @Override
    public User findById(String id) {
        return (User)hashOperations.get("USER:ID",id);
    }

    @Override
    public void updateUser(User user) {
        save(user);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("USER:ID",id);
    }
}
