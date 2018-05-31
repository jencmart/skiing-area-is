package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaAddress;

import java.util.List;

public interface SlaAddressDao {

    void create(SlaAddress address);

    SlaAddress getOneAddress(Long id);

    List<SlaAddress> getAllAddresses();

    void delete(SlaAddress address);

    boolean deleteById(Long id);

    void update(SlaAddress address);
}


