package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sla_order_skipass_article")
public class SlaOrderSkipassArticle {

    @Id
    @Column(name = "id_order_skipass_article")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderSkipasArticle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sla_order_id_order", nullable = false)
    private SlaOrder order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_skipass", nullable = false)
    private SlaSkipass skipass;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //TODO ZMENA
    private List<SlaRent> rents;

    @Column(name = "card_rented")
    private boolean cardRented;

    @Column(name = "card_returned")
    private boolean cardReturned;

    public Long getIdOrderSkipasArticle() {
        return idOrderSkipasArticle;
    }

    public void setIdOrderSkipasArticle(Long idOrderSkipasArticle) {
        this.idOrderSkipasArticle = idOrderSkipasArticle;
    }

    public SlaOrder getOrder() {
        return order;
    }

    public void setOrder(SlaOrder order) {
        this.order = order;
    }

    public SlaSkipass getSkipass() {
        return skipass;
    }

    public void setSkipass(SlaSkipass skipass) {
        this.skipass = skipass;
    }

    public List<SlaRent> getRents() {
        return rents;
    }

    public void setRents(List<SlaRent> rents) {
        this.rents = rents;
    }

    public boolean isCardRented() {
        return cardRented;
    }

    public void setCardRented(boolean cardRented) {
        this.cardRented = cardRented;
    }

    public boolean isCardReturned() {
        return cardReturned;
    }

    public void setCardReturned(boolean cardReturned) {
        this.cardReturned = cardReturned;
    }
}
