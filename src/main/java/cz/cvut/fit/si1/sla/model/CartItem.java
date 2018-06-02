package cz.cvut.fit.si1.sla.model;

import cz.cvut.fit.si1.sla.domain.SlaSkipass;

import java.io.Serializable;

/**
 * Represents cart item which is only saved in session
 */
public class CartItem implements Serializable {

    private SlaSkipass skipass;
    private int count;
    private long price;

    public CartItem(SlaSkipass skipass) {
        this.skipass = skipass;
        this.count = 1;
        this.price = skipass.getPrice();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SlaSkipass getSkipass() {
        return skipass;
    }

    public void setSkipass(SlaSkipass skipass) {
        this.skipass = skipass;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
