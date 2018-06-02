package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaCustomer;

import java.util.List;


/**
 * customer dao
 */
public interface SlaCustomerDao {

    /**
     * Creates customer
     *
     * @param customer customer
     */
    void create(SlaCustomer customer);

    /**
     * Return one customer
     *
     * @param id id of customer
     * @return found customer
     */
    SlaCustomer getOneCustomer(Long id);

    /**
     * Returns all customers
     *
     * @return list of all customers
     */
    List<SlaCustomer> getAllCustomers();

    /**
     * Deteles specified customer
     *
     * @param customer customer
     */
    void delete(SlaCustomer customer);

    /**
     * Delete by id
     *
     * @param id id of customer
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates customer
     *
     * @param customer customer with updated values
     */
    void update(SlaCustomer customer);

    /**
     * Find customers by customer which can have null values
     *
     * @param customer customer with criteria
     * @return list of customers
     */
    List<SlaCustomer> find(SlaCustomer customer);
}