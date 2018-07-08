package com.test.service;

import com.test.entity.User;
import com.test.entity.request.CreateUserRequest;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

   public List<User> listUser() {
       return userRepository.findAll();
   }

   public Boolean isUserExist(String email) {
       User user = userRepository.findByEmail(email);
       return user != null;
   }

   public Optional<User> findById(long id) {
       return userRepository.findById(id);
   }

   public User createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setSalary(createUserRequest.getSalary());
        String type;
        if (createUserRequest.getSalary() < 30000) {
            type = "Silver";
        } else if (createUserRequest.getSalary() < 50000) {
            type = "Gold";
        } else {
            type = "Platinum";
        }
        user.setTypeOfUser(type);
        return userRepository.save(user);
   }
}
