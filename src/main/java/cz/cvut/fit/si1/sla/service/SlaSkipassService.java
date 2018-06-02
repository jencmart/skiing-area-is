package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaSkipass;

import java.util.List;

/**
 * Used to manage skipass
 */
public interface SlaSkipassService {
    /**
     * Returns all skipasses
     *
     * @return list of all skipasses
     */
    List<SlaSkipass> getAllList();

    /**
     * Saves or updates skipass
     *
     * @param skipass skipass
     */
    void saveOrUpdate(SlaSkipass skipass);

    /**
     * Deletes specified skipass
     *
     * @param skipass skipass to be deleted
     */
    void delete(SlaSkipass skipass);

    /**
     * Deletes specified skipass by id
     *
     * @param id id of skipass
     * @return true when successfully deleted
     */
    boolean deleteById(long id);

    /**
     * Finds skipass by id
     *
     * @param id id of skipass
     * @return returns found skipass
     */
    SlaSkipass findById(long id);
}