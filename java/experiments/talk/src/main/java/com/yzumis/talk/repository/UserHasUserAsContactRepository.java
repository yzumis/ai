package com.yzumis.talk.repository;

import com.yzumis.talk.model.userhasuserascontact.UserHasUserAsContact;
import com.yzumis.talk.model.userhasuserascontact.UserHasUserAsContactId;
import org.springframework.data.repository.CrudRepository;

public interface UserHasUserAsContactRepository extends CrudRepository<UserHasUserAsContact, UserHasUserAsContactId> {
}
