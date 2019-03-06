package com.yzumis.talk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yzumis.talk.model.user.User;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username: ?0, password: ?1 }")
    User login(final String username, final String password);

}
