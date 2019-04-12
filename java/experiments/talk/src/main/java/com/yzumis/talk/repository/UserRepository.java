package com.yzumis.talk.repository;


import com.yzumis.talk.model.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT iduser FROM user WHERE username = :username")
    Integer selectIduserByUsername(@Param("username") final String username);

    @Query("SELECT iduser FROM user WHERE username = :username AND password = :password")
    Integer selectIduserByUsernameAndPassword(@Param("username") final String username, @Param("password") final String password);

    @Query("SELECT iduser FROM user WHERE token = :token")
    Integer selectIduserByToken(@Param("token") final String token);

    @Transactional
    @Query("UPDATE user SET token = :token WHERE iduser = :iduser")
    @Modifying
    void updateTokenById(@Param("iduser") final Integer iduser, @Param("token") final String token);

    @Transactional
    @Query("UPDATE user SET main_conversation_idconversation = :main_conversation_idconversation WHERE iduser = :iduser")
    @Modifying
    void updateMainConversationIdConversationById(@Param("iduser") final Integer iduser, @Param("main_conversation_idconversation") final Integer main_conversation_idconversation);

    @Query("SELECT" +
            " u " +
            "FROM" +
            " user_has_user_as_contact uhuc " +
            " INNER JOIN user u ON u.iduser = uhuc.user_iduser_contact "+
            "WHERE" +
            " uhuc.user_iduser = :iduser" +
            " AND u.username LIKE CONCAT('%',:usernameFilter,'%')" +
            "ORDER BY" +
            " u.username")
    List<User> selectUserContactsByIdUserAndUsernameFilter(@Param("iduser") final Integer iduser, @Param("usernameFilter") final String usernameFilter);

    @Query("SELECT u FROM user u WHERE u.username LIKE CONCAT('%',:usernameFilter,'%') ORDER BY u.username")
    List<User> selectUsersByUsernameFilter(@Param("usernameFilter") final String usernameFilter);

}
