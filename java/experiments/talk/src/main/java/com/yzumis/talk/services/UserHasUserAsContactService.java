package com.yzumis.talk.services;

import com.yzumis.talk.model.userhasuserascontact.UserHasUserAsContact;
import com.yzumis.talk.repository.UserHasUserAsContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHasUserAsContactService {

    @Autowired
    private UserHasUserAsContactRepository userHasUserAsContactRepository;

    public void save(final UserHasUserAsContact userHasUserAsContact) {
        userHasUserAsContactRepository.save(userHasUserAsContact);
    }

    public void delete(final UserHasUserAsContact userHasUserAsContact) {
        userHasUserAsContactRepository.delete(userHasUserAsContact);
    }

}
