package com.yzumis.talk.services;

import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserLogin;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User register(final UserRegister userRegister) {
        final User user = new User(userRegister);
        userRepository.selectIdByUsernameAndPassword(userRegister.getUsername(), user.getPassword());


    }

    public User login(final UserLogin userLogin) {


    }
}
