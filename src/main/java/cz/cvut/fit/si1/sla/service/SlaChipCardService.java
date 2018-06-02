package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaChipCard;

import java.util.List;

/**
 * Used to manage chip cards
 */
public interface SlaChipCardService {

    /**
     * Returns all chip cards
     *
     * @return list of all chip cards
     */
    List<SlaChipCard> getAllList();

    /**
     * Saves or updates chip card
     *
     * @param chipCard chip card
     */
    void saveOrUpdate(SlaChipCard chipCard);

    /**
     * Deletes chip card
     *
     * @param chipCard chip card
     */
    void delete(SlaChipCard chipCard);

    /**
     * Deletes chip card with id
     *
     * @param id id of chip card which has to be deleted
     */
    void deleteById(long id);

    /**
     * Find chip card by id
     *
     * @param id id of chip card which has to be found
     * @return found chip card
     */
    SlaChipCard findById(long id);

    /**
     * Finds chip card by RFID
     *
     * @param rfid rfid of chip card
     * @return found chip card
     */
    SlaChipCard findByRfidId(String rfid);

    /**
     * Finds chip by fetch method
     *
     * @param rfid rfid of chipcard
     * @return found chipcard
     */
    SlaChipCard findByRfidIdFetch(String rfid);
}
