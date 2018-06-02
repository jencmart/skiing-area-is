package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Order skipass article dao
 */
@Transactional
public interface SlaOrderSkipassArticleDao {

    /**
     * Creates order skipass article
     *
     * @param orderSkipassArticle order skipass article
     */
    void create(SlaOrderSkipassArticle orderSkipassArticle);

    /**
     * Returns order skipass article by id
     *
     * @param id id of order skipass article
     * @return found order skipass article
     */
    SlaOrderSkipassArticle getOneOrderSkipassArticle(Long id);

    /**
     * Returns all order skipass articles
     *
     * @return list of all order skipass articles
     */
    List<SlaOrderSkipassArticle> getAllOrderSkipassArticles();

    /**
     * Deletes specified order skipass article
     *
     * @param orderSkipassArticle order skipass article
     */
    void delete(SlaOrderSkipassArticle orderSkipassArticle);

    /**
     * Deletes order skipass article by id
     *
     * @param id id of order skipass article
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates order skipass article
     *
     * @param orderSkipassArticle order skipass article with updated values
     */
    void update(SlaOrderSkipassArticle orderSkipassArticle);
}


