package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import cz.cvut.fit.si1.sla.model.CartItem;
import cz.cvut.fit.si1.sla.model.ShoppingCart;

import java.util.List;

/**
 * Used to manage shopping cart
 */
public interface ShoppingCartService {

    /**
     * Used to calculate price of shopping cart contents
     *
     * @param sessionShoppingCart current shopping cart
     */
    void recalculatePrice(ShoppingCart sessionShoppingCart);

    /**
     * Adds skipass to shopping cart
     *
     * @param sessionShoppingCart current shopping cart
     * @param skipass             skipass to be added
     */
    void addToCart(ShoppingCart sessionShoppingCart, SlaSkipass skipass);

    /**
     * Removes skipass from shopping cart
     *
     * @param sessionShoppingCart current shopping cart
     * @param skipass             skipass to be removed
     */
    void removeFromCart(ShoppingCart sessionShoppingCart, SlaSkipass skipass);

    /**
     * Removes every item from shopping cart
     *
     * @param sessionShoppingCart current shopping cart
     */
    void removeAll(ShoppingCart sessionShoppingCart);

    /**
     * Counts number of items in cart
     *
     * @param sessionShoppingCart current shopping cart
     * @return number of items in cart
     */
    int nuberOfItems(ShoppingCart sessionShoppingCart);

    /**
     * Gets every item in cart
     *
     * @param sessionShoppingCart current shopping cart
     * @return contents of shopping cart
     */
    List<CartItem> getAllList(ShoppingCart sessionShoppingCart);

    /**
     * Sets the count of certain skipass in shopping cart
     *
     * @param sessionShoppingCart current shopping cart
     * @param skipass             skipas of which the count will be edited
     * @param count               changes count to this value
     */
    void setCountOfItem(ShoppingCart sessionShoppingCart, SlaSkipass skipass, int count);
}
