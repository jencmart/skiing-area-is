package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;

/**
 * Represents rented card in the database
 */
@Entity
@Table(name = "sla_rent")
public class SlaRent {

    @Id
    @Column(name = "id_rent")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRent;

    @Column(name = "from_date")
    private java.sql.Timestamp fromDate;

    @Column(name = "to_date")
    private java.sql.Timestamp toDate;

    @Column(name = "deposit_paid")
    private boolean depositPaid;

    @Column(name = "deposit_returned")
    private boolean depositReturned;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order_skipass_article", nullable = false)
    private SlaOrderSkipassArticle article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chip_card")
    private SlaChipCard chipCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee", nullable = false)
    private SlaEmployee employee;

    public Long getIdRent() {
        return idRent;
    }

    public void setIdRent(Long idRent) {
        this.idRent = idRent;
    }

    public java.sql.Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(java.sql.Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public java.sql.Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(java.sql.Timestamp toDate) {
        this.toDate = toDate;
    }

    public boolean getDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public boolean getDepositReturned() {
        return depositReturned;
    }

    public void setDepositReturned(boolean depositReturned) {
        this.depositReturned = depositReturned;
    }

    public SlaOrderSkipassArticle getArticle() {
        return article;
    }

    public void setArticle(SlaOrderSkipassArticle article) {
        this.article = article;
    }

    public SlaChipCard getChipCard() {
        return chipCard;
    }

    public void setChipCard(SlaChipCard chipCard) {
        this.chipCard = chipCard;
    }

    public SlaEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(SlaEmployee employee) {
        this.employee = employee;
    }
}
