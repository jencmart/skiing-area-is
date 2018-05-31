package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sla_customer")
public class SlaCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_customer;

    @Column(name = "name")
    @Size(max = 255)
    private String name;

    @Column(name = "surname")
    @Size(max = 255)
    private String surname;

    @Column(name = "email")
    @Size(max = 255)
    private String email;

    @Column(name = "phone")
    @Size(max = 255)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaAddress> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SlaUser> user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaOrder> orders;

    public Long getId_customer() {
        return id_customer;
    }

    public void setId_customer(Long id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SlaOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<SlaOrder> orders) {
        this.orders = orders;
    }

    public List<SlaAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<SlaAddress> addresses) {
        this.addresses = addresses;
    }

    public List<SlaUser> getUser() {
        return user;
    }

    public void setUser(List<SlaUser> user) {
        this.user = user;
    }
}
