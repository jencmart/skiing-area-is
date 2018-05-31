package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sla_address")
public class SlaAddress {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

    @Column(name = "street")
    @Size(max = 255)
    private String street;

    @Column(name = "city")
    @Size(max = 255)
    private String city;

    @Column(name = "postal_code")
    @Size(max = 255)
    private String postalCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer") // id_address in customer is FK
    private SlaCustomer customer;

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public SlaCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SlaCustomer customer) {
        this.customer = customer;
    }
}
