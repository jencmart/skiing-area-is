package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaRoleDao;
import cz.cvut.fit.si1.sla.domain.SlaRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaRoleDao")
public class SlaRoleDaoImpl extends AbstractDao implements SlaRoleDao {

    @Override
    public void create(SlaRole role) {
        getSession().save(role);
    }

    @Override
    public SlaRole getOneRole(Long id) {
        return (SlaRole) getSession().createCriteria(SlaRole.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public SlaRole getRoleByName(String roleName) {
        return (SlaRole) getSession().createCriteria(SlaRole.class).add(Restrictions.eq("roleName", roleName)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaRole> getAllRoles() {
        Criteria criteria = getSession().createCriteria(SlaRole.class);
        return (List<SlaRole>) criteria.list();
    }

    @Override
    public void delete(SlaRole role) {
        getSession().delete(role);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaRole role = getOneRole(id);
        if (role == null)
            return false;
        delete(role);
        return true;
    }

    @Override
    public void update(SlaRole role) {
        getSession().update(role);
    }
}
