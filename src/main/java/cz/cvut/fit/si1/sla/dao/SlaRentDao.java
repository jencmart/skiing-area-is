package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaRent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Rent dao
 */
@Transactional
public interface SlaRentDao {

    /**
     * Creates rent
     *
     * @param slaRent rent
     */
    void create(SlaRent slaRent);

    /**
     * Returns rent specified by id
     *
     * @param id id of rent
     * @return found rent
     */
    SlaRent getOneRent(Long id);

    /**
     * Returns all rents
     *
     * @return list of all rents
     */
    List<SlaRent> getAllRents();

    /**
     * Deletes specified rent
     *
     * @param slaRent rent
     */
    void delete(SlaRent slaRent);

    /**
     * Deletes rent by id
     *
     * @param id id of rent
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates rent
     *
     * @param slaRent rent with updated values
     */
    void update(SlaRent slaRent);
}

