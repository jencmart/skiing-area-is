package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User dao
 */
@Transactional
public interface SlaUserDao {

    /**
     * Creates user
     *
     * @param user user
     */
    void create(SlaUser user);

    /**
     * Returns user by id
     *
     * @param id id of user
     * @return found user
     */
    SlaUser getOneUser(Long id);

    /**
     * Returns user by username
     *
     * @param username username of user
     * @return found user
     */
    SlaUser getUserByUsername(String username);

    /**
     * Returns all users
     *
     * @return list of all users
     */
    List<SlaUser> getAllUsers();

    /**
     * Deletes specified user
     *
     * @param user user
     */
    void delete(SlaUser user);

    /**
     * Deletes user by id
     *
     * @param id id of user
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates user
     *
     * @param user user with updated values
     */
    void update(SlaUser user);
}
