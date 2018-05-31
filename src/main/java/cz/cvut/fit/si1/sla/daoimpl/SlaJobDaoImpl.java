package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaJobDao;
import cz.cvut.fit.si1.sla.domain.SlaJob;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaJobDao")
public class SlaJobDaoImpl extends AbstractDao implements SlaJobDao {
    @Override
    public void create(SlaJob job) {
        getSession().save(job);
    }

    @Override
    public SlaJob getOneJob(Long id) {
        return (SlaJob) getSession().createCriteria(SlaJob.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaJob> getAllJobs() {
        Criteria criteria = getSession().createCriteria(SlaJob.class);
        return (List<SlaJob>) criteria.list();
    }

    @Override
    public void delete(SlaJob job) {
        getSession().delete(job);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaJob job = getOneJob(id);
        if (job == null)
            return false;
        delete(job);
        return true;
    }

    @Override
    public void update(SlaJob job) {
        getSession().update(job);
    }
}
