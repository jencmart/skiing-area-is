package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaOrderDao;
import cz.cvut.fit.si1.sla.domain.SlaOrder;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaOrderDao")
public class SlaOrderDaoImpl extends AbstractDao implements SlaOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(SlaOrder order) {
        getSession().save(order);
    }

    @Override
    public SlaOrder getOneOrder(Long id) {

        Session s = sessionFactory.openSession();
        SlaOrder order = (SlaOrder) s.createCriteria(SlaOrder.class).add(Restrictions.idEq(id)).uniqueResult();
        Hibernate.initialize(order.getArticles());
        s.close();
        return order;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaOrder> getAllOrders() {
        Session s = sessionFactory.openSession();

        Criteria criteria = s.createCriteria(SlaOrder.class);
        List<SlaOrder> slaOrders = (List<SlaOrder>) criteria.list();

        for (SlaOrder order : slaOrders) {
            Hibernate.initialize(order.getArticles());
            order.getArticles().size();
        }

        s.close();
        return slaOrders;
    }

    @Override
    public void delete(SlaOrder order) {
        getSession().delete(order);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaOrder address = getOneOrder(id);
        if (address == null)
            return false;
        delete(address);
        return true;
    }

    @Override
    public void update(SlaOrder order) {
        getSession().update(order);
    }

    @Override
    public List<SlaOrder> getOrdersOfCustomer(Long id_customer) {
        Session s = sessionFactory.openSession();

        Criteria orderCriterie = s.createCriteria(SlaOrder.class);

        Criteria customerCriteria = orderCriterie.createCriteria("customer", "c");
        customerCriteria.add(Restrictions.idEq(id_customer));

        List<SlaOrder> slaOrders = (List<SlaOrder>) orderCriterie.list();

        for (SlaOrder order : slaOrders) {
            Hibernate.initialize(order.getArticles());
            order.getArticles().size();
        }

        s.close();

        return slaOrders;
    }

}
