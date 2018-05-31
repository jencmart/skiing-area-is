package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaCustomer;

import java.util.List;

public interface SlaCustomerDao {

    void create(SlaCustomer customer);

    SlaCustomer getOneCustomer(Long id);

    List<SlaCustomer> getAllCustomers();

    void delete(SlaCustomer customer);

    boolean deleteById(Long id);

    void update(SlaCustomer customer);

    List<SlaCustomer> find(SlaCustomer customer);
}


