package com.yzumis.talk.services;

import com.yzumis.talk.exception.AuthenticationException;
import com.yzumis.talk.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private static final int TOKEN_LENGTH = 100;

    @Autowired
    private UserRepository userRepository;

    public void generateToken(final Integer iduser) {
        final String token = RandomStringUtils.randomAlphanumeric(TOKEN_LENGTH);
        userRepository.updateTokenById(iduser, token);
    }

    public void checkTokenValid(final Integer iduser, final String token) {
        if (iduser != userRepository.selectIduserByToken(token)) {
            throw new AuthenticationException();
        }
    }

}
