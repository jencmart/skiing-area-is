package cz.cvut.fit.si1.sla.dao;


import cz.cvut.fit.si1.sla.domain.SlaUserRole;

import java.util.List;

/**
 * User role dao
 */
public interface SlaUserRoleDao {

    /**
     * Created user role
     *
     * @param userRole user role
     */
    void create(SlaUserRole userRole);

    /**
     * Returns user role by id
     *
     * @param id id of user role
     * @return found user role
     */
    SlaUserRole getOneUserRole(Long id);

    /**
     * Returns all user roles
     *
     * @return list of all user roles
     */
    List<SlaUserRole> getAllUserRoles();

    /**
     * Deletes specified user role
     *
     * @param userRole user role
     */
    void delete(SlaUserRole userRole);

    /**
     * Deletes user role by id
     *
     * @param id id of user role
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates user role
     *
     * @param userRole user role with updated values
     */
    void update(SlaUserRole userRole);
}
