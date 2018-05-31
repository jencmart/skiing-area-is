package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaUserDao;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaUserDao")
public class SlaUserDaoImpl extends AbstractDao implements SlaUserDao {


    @Override
    public void create(SlaUser user) {
        getSession().save(user);
    }

    @Override
    public SlaUser getOneUser(Long id) {
        return (SlaUser) getSession().createCriteria(SlaUser.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public SlaUser getUserByUsername(String username) {
        Criteria criteria = getSession().createCriteria(SlaUser.class).add(Restrictions.eq("username", username));
        return (SlaUser) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaUser> getAllUsers() {
        Criteria criteria = getSession().createCriteria(SlaUser.class);
        return (List<SlaUser>) criteria.list();
    }

    @Override
    public void delete(SlaUser user) {
        getSession().delete(user);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaUser user = getOneUser(id);
        if (user == null)
            return false;
        delete(user);
        return true;
    }

    @Override
    public void update(SlaUser user) {
        getSession().update(user);
    }
}
