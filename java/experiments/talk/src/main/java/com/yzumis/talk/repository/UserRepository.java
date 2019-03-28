package com.yzumis.talk.repository;


import com.yzumis.talk.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT id FROM user WHERE username = :username AND password = :password")
    Integer selectIdByUsernameAndPassword(@Param("username") final String username, @Param("password") final String password);

    @Query("UPDATE user SET token = :token WHERE id = :id")
    void updateTokenById(@Param("id") final Integer id, @Param("token") final String token);

    @Query("SELECT id, username, token FROM user WHERE id = :id")
    User selectUserExceptPasswordById(@Param("id") final Integer id);

}
