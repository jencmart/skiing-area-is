package cz.cvut.fit.si1.sla.dto;

public class RentDto {

    private int orderId;
    private int articleId;
    private int chipCardId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getChipCardId() {
        return chipCardId;
    }

    public void setChipCardId(int chipCardId) {
        this.chipCardId = chipCardId;
    }
}
