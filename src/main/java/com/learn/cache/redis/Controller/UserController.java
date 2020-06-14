package com.learn.cache.redis.Controller;

import com.learn.cache.redis.Repository.UserRepositoryImpl;
import com.learn.cache.redis.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("rest/user")
public class UserController {

    private UserRepositoryImpl userRepository;

    public UserController(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public User addAndGetUser(@PathVariable("id") String id, @PathVariable("name") String name) {
        User newUser = new User(id, name, 25000.36);
        userRepository.save(newUser);
        return userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User updateAndGetUser(@PathVariable("id") String id, @PathVariable("name") String name) {
        User newUser = new User(id, name, 2000);
        userRepository.save(newUser);
        return userRepository.findById(id);
    }

    @GetMapping("/all")
    public Map<String, User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public Map<String, User> deleteById(@PathVariable("id") String id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(id);
        }
        return findAll();
    }
}
