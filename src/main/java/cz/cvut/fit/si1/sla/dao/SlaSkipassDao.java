package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaSkipass;

import java.util.List;

/**
 * Skipass dao
 */
public interface SlaSkipassDao {

    /**
     * Creates skipass
     *
     * @param skipass skipass
     */
    void create(SlaSkipass skipass);

    /**
     * Returns skipass by id
     *
     * @param id id of skipass
     * @return found skipass
     */
    SlaSkipass getOneSkipass(Long id);

    /**
     * Returns all skipasses
     *
     * @return list of all skipasses
     */
    List<SlaSkipass> getAllSkipass();

    /**
     * Deletes specified skipass
     *
     * @param skipass skipass
     */
    void delete(SlaSkipass skipass);

    /**
     * Deletes skipass by id
     *
     * @param id id of skipass
     * @return found skipass
     */
    boolean deleteById(Long id);

    /**
     * Updates skipass
     *
     * @param skipass skipass with updated values
     */
    void update(SlaSkipass skipass);
}
