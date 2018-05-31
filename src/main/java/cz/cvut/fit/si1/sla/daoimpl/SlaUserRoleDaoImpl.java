package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaUserRoleDao;
import cz.cvut.fit.si1.sla.domain.SlaUserRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaUserRoleDao")
public class SlaUserRoleDaoImpl extends AbstractDao implements SlaUserRoleDao {

    @Override
    public void create(SlaUserRole userRole) {
        getSession().save(userRole);
    }

    @Override
    public SlaUserRole getOneUserRole(Long id) {
        return (SlaUserRole) getSession().createCriteria(SlaUserRole.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaUserRole> getAllUserRoles() {
        Criteria criteria = getSession().createCriteria(SlaUserRole.class);
        return (List<SlaUserRole>) criteria.list();
    }

    @Override
    public void delete(SlaUserRole userRole) {
        getSession().delete(userRole);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaUserRole userRole = getOneUserRole(id);
        if (userRole == null)
            return false;
        delete(userRole);
        return true;
    }

    @Override
    public void update(SlaUserRole userRole) {
        getSession().update(userRole);
    }
}
