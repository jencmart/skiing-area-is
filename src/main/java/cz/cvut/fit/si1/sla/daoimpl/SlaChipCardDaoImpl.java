package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaChipCardDao;
import cz.cvut.fit.si1.sla.domain.SlaChipCard;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository("slaChipCardDao")
public class SlaChipCardDaoImpl extends AbstractDao implements SlaChipCardDao {


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void create(SlaChipCard chipCard) {
        getSession().save(chipCard);
    }

    @Override
    public SlaChipCard getOneChipCard(Long id) {
        return (SlaChipCard) getSession().createCriteria(SlaChipCard.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public SlaChipCard getChipCardByRfid(String rfid) {
        return (SlaChipCard) getSession().createCriteria(SlaChipCard.class).add(Restrictions.eq("rfidId", rfid)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaChipCard> getAllChipCards() {
        Criteria criteria = getSession().createCriteria(SlaChipCard.class);
        return (List<SlaChipCard>) criteria.list();
    }

    @Override
    public List<SlaChipCard> getCurrentlyRentedChipCards() {
        return new ArrayList<>();
    }

    @Override
    public void delete(SlaChipCard chipCard) {
        getSession().delete(chipCard);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaChipCard card = getOneChipCard(id);
        if (card == null)
            return false;
        delete(card);
        return true;
    }

    @Override
    public void update(SlaChipCard chipCard) {
        getSession().update(chipCard);
    }

    @Override
    public SlaChipCard getChipCardByRfidFetch(String rfid) {


        Session s = sessionFactory.openSession();

        SlaChipCard chipCard = (SlaChipCard) getSession().createCriteria(SlaChipCard.class).add(Restrictions.eq("rfidId", rfid)).uniqueResult();

        Hibernate.initialize(chipCard.getRents());

        s.close();
        return chipCard;
    }
}
