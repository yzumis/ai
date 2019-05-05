package com.yzumis.talk.repository;

import com.yzumis.talk.model.user.UserContact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserContactRepository extends Repository<UserContact, Integer> {

    @Query(value = "WITH " +
            "nonUserContacts AS ( " +
            "    SELECT " +
            "     u.iduser, " +
            "     u.username " +
            "    FROM " +
            "     user u  " +
            "    WHERE " +
            "     u.iduser <> :idUser " +
            "     AND u.username LIKE CONCAT('%', :usernameFilter, '%') " +
            "     AND NOT EXISTS " +
            "     ( " +
            "       SELECT " +
            "        'dummy' " +
            "       FROM " +
            "        user_has_user_as_contact uhuac " +
            "       WHERE " +
            "        uhuac.user_iduser = :idUser " +
            "        AND uhuac.user_iduser_contact = u.iduser " +
            "     ) " +
            " ), " +
            "userContacts AS " +
            " ( " +
            "    SELECT " +
            "     u.iduser, " +
            "     u.username " +
            "    FROM " +
            "     user u " +
            "     INNER JOIN user_has_user_as_contact uhuac ON uhuac.user_iduser_contact = u.iduser " +
            "    WHERE " +
            "     u.username LIKE CONCAT('%', :usernameFilter, '%') " +
            "     AND uhuac.user_iduser = :idUser " +
            "     AND uhuac.user_iduser_contact <> :idUser " +
            " ), " +
            " userContactsWithPendingMessages AS " +
            " ( " +
            "    SELECT " +
            "     uc.iduser,  " +
            "     uc.username " +
            "    FROM " +
            "     userContacts uc " +
            "     INNER JOIN conversation c " +
            "     INNER JOIN user_has_conversation uhc1 ON uhc1.conversation_idconversation = c.idconversation " +
            "     INNER JOIN user_has_conversation uhc2 ON uhc1.conversation_idconversation = c.idconversation " +
            "    WHERE " +
            "     uhc1.user_iduser = :idUser " +
            "     AND uhc2.user_iduser = uc.iduser " +
            "     AND EXISTS " +
            "     ( " +
            "      SELECT" +
            "       'dummy'" +
            "      FROM" +
            "       message m " +
            "      WHERE" +
            "       m.conversation_idconversation = c.idconversation " +
            "       AND m.user_iduser = uc.iduser " +
            "       AND m.timestamp > " +
            "        ( " +
            "         SELECT " +
            "       m1.timestamp " +
            "         FROM " +
            "          message m1 " +
            "         WHERE " +
            "          m1.conversation_idconversation = c.idconversation " +
            "          AND m1.user_iduser = :idUser " +
            "     ORDER BY " +
            "      timestamp DESC\n" +
            "     LIMIT 1 " +
            "        ) " +
            "     ) " +
            " ), " +
            " userContactsWithNoPendingMessages AS " +
            " ( " +
            "    SELECT " +
            "     uc.iduser,  " +
            "     uc.username  " +
            "    FROM " +
            "     userContacts uc " +
            "    WHERE " +
            "     NOT EXISTS " +
            "     ( " +
            "       SELECT " +
            "        'dummy' " +
            "       FROM " +
            "        userContactsWithPendingMessages ucwpm " +
            "       WHERE " +
            "        ucwpm.iduser = uc.iduser " +
            "     ) " +
            " ), " +
            " allContactsUnion AS " +
            " ( " +
            "    SELECT " +
            "      ucwpm.iduser,  " +
            "      ucwpm.username, " +
            "      true AS contact, " +
            "      true AS pendingMessages  " +
            "     FROM " +
            "      userContactsWithPendingMessages ucwpm " +
            "    UNION " +
            "     SELECT " +
            "      ucwnpm.iduser,  " +
            "      ucwnpm.username, " +
            "      true AS contact, " +
            "      false AS pendingMessages " +
            "     FROM " +
            "      userContactsWithNoPendingMessages ucwnpm " +
            "    UNION " +
            "     SELECT " +
            "      nuc.iduser, " +
            "      nuc.username, " +
            "      false AS contact, " +
            "      false AS pendingMessages " +
            "     FROM " +
            "      nonUserContacts nuc " +
            " ) " +
            "SELECT " +
            " acu.iduser AS id, " +
            " acu.iduser, " +
            " acu.username, " +
            " acu.contact, " +
            " acu.pendingMessages AS pending_messages " +
            "FROM " +
            " allContactsUnion acu " +
            "ORDER BY " +
            " acu.pendingMessages DESC, " +
            " acu.contact DESC, " +
            " acu.username ASC",
            nativeQuery = true
    )
    List<UserContact> selectUsersByUsernameFilter(@Param("idUser") final Integer idUser, @Param("usernameFilter") final String usernameFilter);
}
