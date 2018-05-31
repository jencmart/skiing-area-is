package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaRentDao;
import cz.cvut.fit.si1.sla.domain.SlaRent;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaRentDao")
public class SlaRentDaoImpl extends AbstractDao implements SlaRentDao {
    @Override
    public void create(SlaRent slaRent) {
        getSession().save(slaRent);
    }

    @Override
    public SlaRent getOneRent(Long id) {
        return (SlaRent) getSession().createCriteria(SlaRent.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaRent> getAllRents() {
        Criteria criteria = getSession().createCriteria(SlaRent.class);
        return (List<SlaRent>) criteria.list();
    }

    @Override
    public void delete(SlaRent slaRent) {
        getSession().delete(slaRent);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaRent rent = getOneRent(id);
        if (rent == null)
            return false;
        delete(rent);
        return true;
    }

    @Override
    public void update(SlaRent slaRent) {
        getSession().update(slaRent);
    }
}
