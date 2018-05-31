package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaCustomerDao;
import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaCustomerDao")
public class SlaCustomerDaoImpl extends AbstractDao implements SlaCustomerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(SlaCustomer customer) {
        getSession().save(customer);
    }

    @Override
    public SlaCustomer getOneCustomer(Long id) {

        Session s = sessionFactory.openSession();

        SlaCustomer slaCustomer = (SlaCustomer) getSession().createCriteria(SlaCustomer.class).add(Restrictions.idEq(id)).uniqueResult();

        Hibernate.initialize(slaCustomer.getOrders());
        Hibernate.initialize(slaCustomer.getUser());

        s.close();

        return slaCustomer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaCustomer> getAllCustomers() {
        Session s = sessionFactory.openSession();
        List<SlaCustomer> slaCustomerList = getSession().createCriteria(SlaCustomer.class).list();

        for (SlaCustomer e : slaCustomerList) {
            Hibernate.initialize(e.getUser());
        }

        s.close();
        return slaCustomerList;
    }

    @Override
    public void delete(SlaCustomer customer) {
        getSession().delete(customer);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaCustomer customer = getOneCustomer(id);
        if (customer == null)
            return false;
        delete(customer);
        return true;
    }

    @Override
    public void update(SlaCustomer customer) {
        getSession().update(customer);
    }

    @Override
    public List<SlaCustomer> find(SlaCustomer customer) {

        Criterion name = null;
        Criterion surname = null;
        Criterion phone = null;
        Criterion email = null;

        if (customer.getName() != null && !customer.getName().isEmpty())
            name = Restrictions.ilike("name", "%" + customer.getName() + "%");
        if (customer.getSurname() != null && !customer.getSurname().isEmpty())
            surname = Restrictions.ilike("surname", "%" + customer.getSurname() + "%");
        if (customer.getPhone() != null && !customer.getPhone().isEmpty())
            phone = Restrictions.ilike("phone", "%" + customer.getPhone() + "%");
        if (customer.getEmail() != null && !customer.getEmail().isEmpty())
            email = Restrictions.ilike("email", "%" + customer.getEmail() + "%");

        Criteria criteria = getSession().createCriteria(SlaCustomer.class);

        Conjunction disjunction = Restrictions.conjunction();
        if (name != null)
            disjunction.add(name);
        if (surname != null)
            disjunction.add(surname);
        if (phone != null)
            disjunction.add(phone);
        if (email != null)
            disjunction.add(email);

        criteria.add(disjunction);

        return (List<SlaCustomer>) criteria.list();
    }
}
