package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaRole;

import java.util.List;

public interface SlaRoleDao {

    void create(SlaRole role);

    SlaRole getOneRole(Long id);

    SlaRole getRoleByName(String roleName);

    List<SlaRole> getAllRoles();

    void delete(SlaRole role);

    boolean deleteById(Long id);

    void update(SlaRole role);
}
