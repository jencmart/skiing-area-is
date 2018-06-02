package cz.cvut.fit.si1.sla.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Represents chip card in the database
 */
@Entity
@Table(name = "sla_chip_card")
public class SlaChipCard {

    @Id
    @Column(name = "id_chip_card")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChipCard;

    @Column(name = "rfid_id")
    @Size(max = 255)
    private String rfidId;

    @Column(name = "registered_timestamp")
    private java.sql.Timestamp registeredTimestamp;

    @Column(name = "removed")
    private Double removed;

    @Column(name = "removed_timestamp")
    private java.sql.Timestamp removedTimestamp;

    @Column(name = "deposit_price")
    private Long depositPrice;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "chipCard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaRent> rents;


    @Column(name = "card_rented")
    private boolean cardRented;

    public Long getIdChipCard() {
        return idChipCard;
    }

    public void setIdChipCard(Long idChipCard) {
        this.idChipCard = idChipCard;
    }

    public String getRfidId() {
        return rfidId;
    }

    public void setRfidId(String rfidId) {
        this.rfidId = rfidId;
    }

    public java.sql.Timestamp getRegisteredTimestamp() {
        return registeredTimestamp;
    }

    public void setRegisteredTimestamp(java.sql.Timestamp registeredTimestamp) {
        this.registeredTimestamp = registeredTimestamp;
    }

    public Double getRemoved() {
        return removed;
    }

    public void setRemoved(Double removed) {
        this.removed = removed;
    }

    public java.sql.Timestamp getRemovedTimestamp() {
        return removedTimestamp;
    }

    public void setRemovedTimestamp(java.sql.Timestamp removedTimestamp) {
        this.removedTimestamp = removedTimestamp;
    }

    public Long getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(Long depositPrice) {
        this.depositPrice = depositPrice;
    }

    public List<SlaRent> getRents() {
        return rents;
    }

    public void setRents(List<SlaRent> rents) {
        this.rents = rents;
    }

    public boolean isNew() {
        return (this.idChipCard == null);
    }

    public boolean isCardRented() {
        return cardRented;
    }

    public void setCardRented(boolean cardRented) {
        this.cardRented = cardRented;
    }
}
