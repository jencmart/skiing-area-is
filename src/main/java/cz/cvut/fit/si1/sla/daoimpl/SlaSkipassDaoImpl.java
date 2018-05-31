package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaSkipassDao;
import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository("slaSkipassDao")
public class SlaSkipassDaoImpl extends AbstractDao implements SlaSkipassDao {

    @Override
    public void create(SlaSkipass skipass) {
        getSession().save(skipass);
    }

    @Override
    public SlaSkipass getOneSkipass(Long id) {
        return (SlaSkipass) getSession().createCriteria(SlaSkipass.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaSkipass> getAllSkipass() {
        Criteria criteria = getSession().createCriteria(SlaSkipass.class);
        return (List<SlaSkipass>) criteria.list();
    }

    @Override
    public void delete(SlaSkipass skipass) {
        getSession().delete(skipass);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaSkipass skipass = getOneSkipass(id);
        if (id == null)
            return false;
        delete(skipass);
        return true;
    }

    @Override
    public void update(SlaSkipass skipass) {
        getSession().update(skipass);
    }
}
