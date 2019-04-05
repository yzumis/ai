package com.yzumis.talk.services;

import com.yzumis.talk.expception.IncorrectUserOrPasswordException;
import com.yzumis.talk.expception.UserAlreadyRegisteredException;
import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserContact;
import com.yzumis.talk.model.user.UserLogin;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    public List<UserContact> users(final Integer iduser, final String usernameFilter) {
        final List<UserContact> ret = new ArrayList<>();
        final List<User> contactList = userRepository.selectUserContactsByIdUserAndUsernameFilter(iduser, usernameFilter);
        final List<User> nonContactList = userRepository.selectUsersByUsernameFilter(usernameFilter);
        contactList.stream().forEach(user -> ret.add(new UserContact(user, true)));
        nonContactList
                .stream()
                .filter(user -> !this.idUserInUserContactList(user.getIduser(), contactList))
                .forEach(user -> ret.add(new UserContact(user, false)));
        return ret;
    }

    private boolean idUserInUserContactList(final Integer iduser, final List<User> contactList) {
        return contactList.stream().anyMatch(user -> user.getIduser() == iduser);
    }

}
