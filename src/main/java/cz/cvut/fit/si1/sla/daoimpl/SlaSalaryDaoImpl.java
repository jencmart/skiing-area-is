package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaSalaryDao;
import cz.cvut.fit.si1.sla.domain.SlaSalary;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository("slaSalaryDao")
public class SlaSalaryDaoImpl extends AbstractDao implements SlaSalaryDao {
    @Override
    public void create(SlaSalary salary) {
        getSession().save(salary);
    }

    @Override
    public SlaSalary getOneSalary(Long id) {
        return (SlaSalary) getSession().createCriteria(SlaSalary.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaSalary> getAllsSalaries() {
        Criteria criteria = getSession().createCriteria(SlaSalary.class);
        return (List<SlaSalary>) criteria.list();
    }

    @Override
    public void delete(SlaSalary salary) {
        getSession().delete(salary);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaSalary salary = getOneSalary(id);
        if (salary == null)
            return false;
        delete(salary);
        return true;
    }

    @Override
    public void update(SlaSalary salary) {
        getSession().update(salary);
    }
}
