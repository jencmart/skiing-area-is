package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SlaOrderSkipassArticleDao {

    void create(SlaOrderSkipassArticle orderSkipassArticle);

    SlaOrderSkipassArticle getOneOrderSkipassArticle(Long id);

    List<SlaOrderSkipassArticle> getAllOrderSkipassArticles();

    void delete(SlaOrderSkipassArticle orderSkipassArticle);

    boolean deleteById(Long id);

    void update(SlaOrderSkipassArticle orderSkipassArticle);
}


