package com.yzumis.talk.services;

import com.yzumis.talk.exception.IncorrectUserOrPasswordException;
import com.yzumis.talk.exception.UserAlreadyRegisteredException;
import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserContact;
import com.yzumis.talk.model.user.UserLogin;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.repository.UserContactRepository;
import com.yzumis.talk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserContactRepository userContactRepository;

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

    public List<UserContact> users(final Integer idUser, final String usernameFilter) {
        final List<UserContact> ret = userContactRepository.selectUsersByUsernameFilter(idUser, usernameFilter);
        return ret;
    }

    public Integer mainConversationByIdUser(final Integer idUser) {
        final Integer ret;
        final Optional<User> userOptional = userRepository.findById(idUser);
        if(userOptional.isPresent()) {
            ret = userOptional.get().getMain_conversation_idconversation();
        } else {
            ret = null;
        }
        return ret;
    }

    public void updateMainConversationIdConversationById(final Integer idUser, final Integer idConversation) {
        userRepository.updateMainConversationIdConversationById(idUser, idConversation);
    }

}
