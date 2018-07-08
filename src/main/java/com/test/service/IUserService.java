package com.test.service;

import com.test.entity.User;
import com.test.entity.request.CreateUserRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> listUser();

    User createUser(CreateUserRequest createUserRequest);

    Boolean isUserExist(String email);

    Optional<User> findById(long id);
}
