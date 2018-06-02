package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaOrder;

import java.util.List;

/**
 * Order dao
 */
public interface SlaOrderDao {

    /**
     * Creates order
     *
     * @param order order
     */
    void create(SlaOrder order);

    /**
     * Returns order by id
     *
     * @param id id of order
     * @return found order
     */
    SlaOrder getOneOrder(Long id);

    /**
     * Returns all orders
     *
     * @return list of all orders
     */
    List<SlaOrder> getAllOrders();

    /**
     * Deletes specified order
     *
     * @param order order
     */
    void delete(SlaOrder order);

    /**
     * Deletes order by id
     *
     * @param id id of order
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates order
     *
     * @param order order with updated values
     */
    void update(SlaOrder order);

    /**
     * Returns orders that belong to certain customer
     *
     * @param id_customer id of customer
     * @return all orders that belong to specified customer
     */
    List<SlaOrder> getOrdersOfCustomer(Long id_customer);
}

