package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaChipCard;

import java.util.List;

/**
 * Chip card dao
 */
public interface SlaChipCardDao {

    /**
     * Create chip card
     *
     * @param chipCard chip card to be created
     */
    void create(SlaChipCard chipCard);

    /**
     * Find one chip card
     *
     * @param id id of chip card
     * @return found chip card
     */
    SlaChipCard getOneChipCard(Long id);

    /**
     * Gets chip card by rfid
     *
     * @param rfid rfid of chip card
     * @return found chip card
     */
    SlaChipCard getChipCardByRfid(String rfid);

    /**
     * Returns all chip cards
     *
     * @return list of all chip cards
     */
    List<SlaChipCard> getAllChipCards();

    /**
     * Returns currently rented chip cards
     *
     * @return list of currently rented chip cards
     */
    List<SlaChipCard> getCurrentlyRentedChipCards();

    /**
     * Deletes specified chip card
     *
     * @param chipCard chip card to be deleted
     */
    void delete(SlaChipCard chipCard);

    /**
     * Deletes chip card by id
     *
     * @param id id of chip card
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates chip card
     *
     * @param chipCard updated chip card
     */
    void update(SlaChipCard chipCard);

    /**
     * Returns chip card via fetch method
     *
     * @param rfid rfid of chip card
     * @return found chip card
     */
    SlaChipCard getChipCardByRfidFetch(String rfid);
}
