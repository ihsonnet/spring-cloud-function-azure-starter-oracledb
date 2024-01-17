package com.poc.function.service;

import com.poc.function.dto.GreetingResponse;
import com.poc.function.model.User;
import com.poc.function.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    List<User> users = new ArrayList<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setId("1");
        user.setName("John");
        user.setEmail("service@gmail.com");
        user.setPhone("1234567890");
        user.setAddress("123 Main St, Anytown USA");
        users.add(user);
    }

    public List<User> getUsers(String name) {
        List<User> userList = new ArrayList<>();
        System.out.println("name: " + name);
        userList = userRepository.getUsers();
        return userList;
    }

    public GreetingResponse saveUser(User user) {
        try {
            user.setId(String.valueOf(userRepository.getNextSequenceId()));
            userRepository.saveUser(user);
        } catch (Exception e) {
            return new GreetingResponse(e.getMessage());
        }
        return new GreetingResponse(user.getName());
    }
}
