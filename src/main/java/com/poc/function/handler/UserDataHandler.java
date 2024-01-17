package com.poc.function.handler;


import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.poc.function.functions.BulkDataQueryFunction;
import com.poc.function.model.User;
import com.poc.function.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataHandler {

    @Autowired
    private BulkDataQueryFunction bulkDataQueryFunction;


    @FunctionName("getUsers")
    public HttpResponseMessage getUsers(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            // optional query parameter
//            @BindingName("name") Optional<String> name,
            ExecutionContext context) {
            context.getLogger().info("Greeting user by name: ");
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body(bulkDataQueryFunction.getUser().apply("name"))
                    .header("Content-Type", "application/json")
                    .build();
    }

    @FunctionName("saveUser")
    public HttpResponseMessage saveUser(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            ExecutionContext context) {
        if (request.getBody().isEmpty()) {
            return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Please pass a valid user object")
                    .header("Content-Type", "application/json")
                    .build();
        }
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(bulkDataQueryFunction.saveUser().apply(request.getBody().get()))
                .header("Content-Type", "application/json")
                .build();
    }

//    @FunctionName("printHello")
//    public void printHello(
//            @TimerTrigger(name = "timerInfo", schedule = "0/5 * * * * *") String timerInfo,
//            ExecutionContext context) {
//            context.getLogger().info("Timer trigger function executed at: " + java.time.LocalDateTime.now());
//    }

}
