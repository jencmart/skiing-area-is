package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaAddressDao;
import cz.cvut.fit.si1.sla.domain.SlaAddress;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaAddressDao")
public class SlaAddressDaoImpl extends AbstractDao implements SlaAddressDao {

    @Override
    public void create(SlaAddress address) {
        getSession().save(address);
    }

    @Override
    public SlaAddress getOneAddress(Long id) {
        return (SlaAddress) getSession().createCriteria(SlaAddress.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaAddress> getAllAddresses() {
        Criteria criteria = getSession().createCriteria(SlaAddress.class);
        return (List<SlaAddress>) criteria.list();
    }

    @Override
    public void delete(SlaAddress address) {
        getSession().delete(address);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaAddress address = getOneAddress(id);
        if (address == null)
            return false;
        delete(address);
        return true;
    }

    @Override
    public void update(SlaAddress address) {
        getSession().update(address);
    }
}
