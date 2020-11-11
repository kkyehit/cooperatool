package com.soongsil.sixfourfour.service;

import com.soongsil.sixfourfour.model.User;
import com.soongsil.sixfourfour.repository.UserRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;

    public User createUser(String name, String phone){
        User user = new User(0, name, phone);
        return user;
    }

    public User modifyUser(long id, String name, String phone){
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ExpressionException("Sdsa"));

        return null;
    }

    public User readUser(long id) {
        User u = new User(id, "dsa", "dsadsa");
        return u;
//        Optional<User> user = userRepository.findById(id);
//        user.orElseThrow(() -> new ExpressionException("dsa"));
//
//        return null;
    }
}
