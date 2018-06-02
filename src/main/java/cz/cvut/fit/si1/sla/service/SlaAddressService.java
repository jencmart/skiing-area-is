package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaAddress;

import java.util.List;

/**
 * Used to manage customer address
 */
public interface SlaAddressService {

    /**
     * Returns all addresses
     *
     * @return list of all addresses
     */
    List<SlaAddress> getAllList();
}
