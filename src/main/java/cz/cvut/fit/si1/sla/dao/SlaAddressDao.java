package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaAddress;

import java.util.List;

/**
 * Address dao
 */
public interface SlaAddressDao {

    /**
     * Creates new address
     *
     * @param address address
     */
    void create(SlaAddress address);

    /**
     * Finds one address by id
     *
     * @param id id of address
     * @return found address
     */
    SlaAddress getOneAddress(Long id);

    /**
     * Returns list of all addresses
     *
     * @return list of all addresses
     */
    List<SlaAddress> getAllAddresses();

    /**
     * Deletes specified address
     *
     * @param address address to be deleted
     */
    void delete(SlaAddress address);

    /**
     * Deleted address by id
     *
     * @param id id of address
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates specified address
     *
     * @param address address with updated contents
     */
    void update(SlaAddress address);
}
