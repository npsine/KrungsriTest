package com.test.controller;

import com.test.service.IUserService;
import com.test.entity.User;
import com.test.entity.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity getAllUsers() {
        try {
            List user = userService.listUser();
            if (user.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<User>>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        Optional<User> user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            User user;
            if (userService.isUserExist(createUserRequest.getEmail())) { // duplicate user
                return new ResponseEntity(HttpStatus.CONFLICT);
            }

            if (createUserRequest.getSalary() < 15000) { // salary < 15,000
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }

            user = userService.createUser(createUserRequest);
            return new ResponseEntity<>(userService.findById(user.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
