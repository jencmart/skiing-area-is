package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaOrder;

import java.util.List;

public interface SlaOrderDao {

    void create(SlaOrder order);

    SlaOrder getOneOrder(Long id);

    List<SlaOrder> getAllOrders();

    void delete(SlaOrder order);

    boolean deleteById(Long id);

    void update(SlaOrder order);

    List<SlaOrder> getOrdersOfCustomer(Long id_customer);
}


