package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.domain.SlaOrder;
import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import cz.cvut.fit.si1.sla.domain.SlaRent;
import cz.cvut.fit.si1.sla.dto.RentDto;
import cz.cvut.fit.si1.sla.model.CartItem;

import java.util.List;

/**
 * Used to manage order
 */
public interface SlaOrderService {
    /**
     * Returns all orders
     *
     * @return all orders
     */
    List<SlaOrder> getAllList();

    /**
     * Find order by id
     *
     * @param id id of order
     * @return found order
     */
    SlaOrder findById(Integer id);

    /**
     * Creates order from cart items
     *
     * @param customer  customer that will be assigned to order
     * @param cartItems items that will be put into order
     * @return created order
     */
    SlaOrder createOrderFromCartItems(SlaCustomer customer, List<CartItem> cartItems);

    /**
     * Gets articles of order
     *
     * @param slaOrder order
     * @return skipass articles of specified order
     */
    List<SlaOrderSkipassArticle> getArticles(SlaOrder slaOrder);

    /**
     * Returns all articles
     *
     * @return list of all skipass articles
     */
    List<SlaOrderSkipassArticle> getAllArticlesList();

    /**
     * Rents cart
     *
     * @param rentDto rent dto
     */
    void rent(RentDto rentDto);

    /**
     * Returns article
     *
     * @param idArticle id of article
     * @return found article
     */
    SlaOrderSkipassArticle getArticle(int idArticle);

    /**
     * Updates specified rent
     *
     * @param rent rent
     */
    void updateRent(SlaRent rent);

    /**
     * Updates specified article
     *
     * @param article article
     */
    void updateArticle(SlaOrderSkipassArticle article);

    /**
     * Gets all orders of customer
     *
     * @param customer customer
     * @return list of all orders belonging to customer
     */
    List<SlaOrder> getOrdersOfCustomer(SlaCustomer customer);

    /**
     * Updates specified order
     *
     * @param order order
     */
    void updateOrder(SlaOrder order);

    /**
     * Calculates price of order
     *
     * @param slaOrder order
     * @return calculated price
     */
    int calculatePrice(SlaOrder slaOrder);
}