package cz.cvut.fit.si1.sla.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart implements Serializable {

    private long totalPrice;

    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
