package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaEmployeeDao;
import cz.cvut.fit.si1.sla.domain.SlaEmployee;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaEmployeeDao")
public class SlaEmployeeDaoImpl extends AbstractDao implements SlaEmployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(SlaEmployee employee) {
        getSession().save(employee);
    }

    @Override
    public SlaEmployee getOneEmployee(Long id) {

        Session s = sessionFactory.openSession();

        SlaEmployee slaEmployee = (SlaEmployee) getSession().createCriteria(SlaEmployee.class).add(Restrictions.idEq(id)).uniqueResult();

        Hibernate.initialize(slaEmployee.getUser());

        s.close();

        return slaEmployee;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaEmployee> getAllEmployees() {
        Session s = sessionFactory.openSession();
        List<SlaEmployee> slaEmployee = getSession().createCriteria(SlaEmployee.class).list();

        for (SlaEmployee e : slaEmployee) {
            Hibernate.initialize(e.getUser());
        }

        s.close();
        return slaEmployee;
    }

    @Override
    public void delete(SlaEmployee employee) {
        getSession().delete(employee);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaEmployee employee = getOneEmployee(id);
        if (employee == null)
            return false;
        delete(employee);
        return true;
    }

    @Override
    public void update(SlaEmployee employee) {
        getSession().update(employee);
    }
}
