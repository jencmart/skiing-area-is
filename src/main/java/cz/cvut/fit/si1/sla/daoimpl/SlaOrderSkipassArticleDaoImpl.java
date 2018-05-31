package cz.cvut.fit.si1.sla.daoimpl;

import cz.cvut.fit.si1.sla.dao.AbstractDao;
import cz.cvut.fit.si1.sla.dao.SlaOrderSkipassArticleDao;
import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("slaOrderSkipassArticleDaoImpl")
public class SlaOrderSkipassArticleDaoImpl extends AbstractDao implements SlaOrderSkipassArticleDao {

    @Override
    public void create(SlaOrderSkipassArticle orderSkipassArticle) {
        getSession().save(orderSkipassArticle);
    }

    @Override
    public SlaOrderSkipassArticle getOneOrderSkipassArticle(Long id) {
        return (SlaOrderSkipassArticle) getSession().createCriteria(SlaOrderSkipassArticle.class).add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlaOrderSkipassArticle> getAllOrderSkipassArticles() {
        Criteria criteria = getSession().createCriteria(SlaOrderSkipassArticle.class);
        return (List<SlaOrderSkipassArticle>) criteria.list();
    }

    @Override
    public void delete(SlaOrderSkipassArticle orderSkipassArticle) {
        getSession().delete(orderSkipassArticle);
    }

    @Override
    public boolean deleteById(Long id) {
        SlaOrderSkipassArticle article = getOneOrderSkipassArticle(id);
        if (article == null)
            return false;
        delete(article);
        return true;
    }

    @Override
    public void update(SlaOrderSkipassArticle orderSkipassArticle) {
        getSession().update(orderSkipassArticle);
    }
}
