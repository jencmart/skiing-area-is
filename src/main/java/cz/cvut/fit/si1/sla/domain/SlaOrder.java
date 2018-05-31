package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sla_order")
public class SlaOrder {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(name = "created")
    private java.sql.Timestamp created;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "paid_datetime")
    private java.sql.Timestamp paidDatetime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer")
    private SlaCustomer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaOrderSkipassArticle> articles;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public java.sql.Timestamp getCreated() {
        return created;
    }

    public void setCreated(java.sql.Timestamp created) {
        this.created = created;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public java.sql.Timestamp getPaidDatetime() {
        return paidDatetime;
    }

    public void setPaidDatetime(java.sql.Timestamp paidDatetime) {
        this.paidDatetime = paidDatetime;
    }

    public SlaCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SlaCustomer customer) {
        this.customer = customer;
    }

    public List<SlaOrderSkipassArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<SlaOrderSkipassArticle> articles) {
        this.articles = articles;
    }
}
