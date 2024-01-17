package com.poc.function.functions;

import com.poc.function.dto.GreetingResponse;
import com.poc.function.model.User;
import com.poc.function.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class BulkDataQueryFunction {

    @Autowired
    private UserService userService;

    @Bean
    public Function<User, GreetingResponse> saveUser() {
        return user -> userService.saveUser(user);
    }

    @Bean
    public Function<String, List<User>> getUser() {
        return name -> userService.getUsers(name);
    }
}
