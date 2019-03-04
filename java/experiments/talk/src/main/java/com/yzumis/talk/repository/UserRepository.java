package com.yzumis.talk.repository;

import org.springframework.data.repository.CrudRepository;

import com.yzumis.talk.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    void login(User user);

}
