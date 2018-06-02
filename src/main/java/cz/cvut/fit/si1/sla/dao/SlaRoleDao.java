package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaRole;

import java.util.List;

/**
 * role dao
 */
public interface SlaRoleDao {

    /**
     * Creates role
     *
     * @param role role
     */
    void create(SlaRole role);

    /**
     * Returns role by id
     *
     * @param id id of role
     * @return found role
     */
    SlaRole getOneRole(Long id);

    /**
     * Returns role by name
     *
     * @param roleName name of role
     * @return found role
     */
    SlaRole getRoleByName(String roleName);

    /**
     * Gets all roles
     *
     * @return list of all roles
     */
    List<SlaRole> getAllRoles();

    /**
     * Deletes specified role
     *
     * @param role role
     */
    void delete(SlaRole role);

    /**
     * Deletes role by id
     *
     * @param id id of role
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates role
     *
     * @param role role with updated values
     */
    void update(SlaRole role);
}
