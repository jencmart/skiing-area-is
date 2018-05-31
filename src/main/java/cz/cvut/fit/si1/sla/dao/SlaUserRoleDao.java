package cz.cvut.fit.si1.sla.dao;


import cz.cvut.fit.si1.sla.domain.SlaUserRole;

import java.util.List;

public interface SlaUserRoleDao {

    void create(SlaUserRole userRole);

    SlaUserRole getOneUserRole(Long id);

    List<SlaUserRole> getAllUserRoles();

    void delete(SlaUserRole userRole);

    boolean deleteById(Long id);

    void update(SlaUserRole userRole);
}
