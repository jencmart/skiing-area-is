package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.dto.CustomerOrderDto;

import java.util.List;

/**
 * Used to manage customers
 */
public interface SlaCustomerService {
    /**
     * Returns all customers
     *
     * @return list of all customers
     */
    List<SlaCustomer> getAllList();

    /**
     * Edits customer with values from dto
     *
     * @param customer         customer to be edited
     * @param customerOrderDto parameters are taken from this dto
     */
    void edit(SlaCustomer customer, CustomerOrderDto customerOrderDto);

    /**
     * Registers new customer with dto
     *
     * @param customerOrderDto values of customer in dto
     * @return created customer
     */
    SlaCustomer registerNewCustomer(CustomerOrderDto customerOrderDto);

    /**
     * Updates customer tied with current user
     *
     * @param customerOrderDto values that will be put into customer
     * @return updated customer
     */
    SlaCustomer updateCustomerUser(CustomerOrderDto customerOrderDto);

    /**
     * Registers new customer that is tied with specified user
     *
     * @param customerOrderDto values that will be put into customer
     * @param user             user
     * @return created customer
     */
    SlaCustomer registerNewCustomerToUser(CustomerOrderDto customerOrderDto, SlaUser user);

    /**
     * Finds customer by id
     *
     * @param id id of customer
     * @return found customer
     */
    SlaCustomer findById(int id);

    /**
     * Finds customers by customer
     *
     * @param customer customer
     * @return list of found customers
     */
    List<SlaCustomer> findCustomer(SlaCustomer customer);
}