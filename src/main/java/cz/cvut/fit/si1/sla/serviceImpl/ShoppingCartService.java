package cz.cvut.fit.si1.sla.serviceImpl;


import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import cz.cvut.fit.si1.sla.model.CartItem;
import cz.cvut.fit.si1.sla.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShoppingCartService {

    private void recalculatePrice(ShoppingCart sessionShoppingCart) {

        long totalPrice = 0;
        for (CartItem item : sessionShoppingCart.getCartItems())
            totalPrice += item.getPrice();
        sessionShoppingCart.setTotalPrice(totalPrice);
    }

    public void addToCart(ShoppingCart sessionShoppingCart, SlaSkipass skipass) {
        for (CartItem item : sessionShoppingCart.getCartItems()) {
            if (Objects.equals(item.getSkipass().getIdSkipass(), skipass.getIdSkipass())) {
                item.setCount(item.getCount() + 1);
                item.setPrice(item.getPrice() + skipass.getPrice());
                recalculatePrice(sessionShoppingCart);
                return;
            }
        }
        sessionShoppingCart.getCartItems().add(new CartItem(skipass));
        recalculatePrice(sessionShoppingCart);
    }

    public void removeFromCart(ShoppingCart sessionShoppingCart, SlaSkipass skipass) {
        List<CartItem> items = sessionShoppingCart.getCartItems();
        for (CartItem item : items) {
            if (Objects.equals(skipass.getIdSkipass(), item.getSkipass().getIdSkipass())) {
                items.remove(item);
                recalculatePrice(sessionShoppingCart);
                return;
            }
        }
    }

    public void removeAll(ShoppingCart sessionShoppingCart) {
        sessionShoppingCart.getCartItems().clear();
        recalculatePrice(sessionShoppingCart);
    }

    public int nuberOfItems(ShoppingCart sessionShoppingCart) {
        int count = 0;
        for (CartItem item : sessionShoppingCart.getCartItems())
            count += item.getCount();
        return count;
    }

    public List<CartItem> getAllList(ShoppingCart sessionShoppingCart) {
        return sessionShoppingCart.getCartItems();
    }

    public void setCountOfItem(ShoppingCart sessionShoppingCart, SlaSkipass skipass, int count) {
        for (CartItem item : sessionShoppingCart.getCartItems()) {
            if (Objects.equals(item.getSkipass().getIdSkipass(), skipass.getIdSkipass())) {
                item.setCount(count);
                item.setPrice(count * skipass.getPrice());
                recalculatePrice(sessionShoppingCart);
                return;
            }
        }
    }

}
