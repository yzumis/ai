package com.yzumis.talk.services;

import com.yzumis.talk.expception.IncorrectUserOrPasswordException;
import com.yzumis.talk.expception.UserAlreadyRegisteredException;
import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserLogin;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    public void register(final UserRegister userRegister) throws UserAlreadyRegisteredException {
        final User user = new User(userRegister);
        if(userAlreadyExists(user)) {
            throw new UserAlreadyRegisteredException();
        } else {
            userRepository.save(user);
        }
    }

    private boolean userAlreadyExists(final User user) {
        return null != userRepository.selectIduserByUsername(user.getUsername());
    }

    public User login(final UserLogin userLogin) throws IncorrectUserOrPasswordException {
        final User ret;
        final Integer iduser = userRepository.selectIduserByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        if(iduser == null) {
            throw new IncorrectUserOrPasswordException();
        } else {
            tokenService.generateToken(iduser);
            final Optional<User> optionalUser = userRepository.findById(iduser);
            ret = optionalUser.get();
            ret.setPassword(null);
        }
        return ret;
    }

}
