package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Represents employee in the database
 */
@Entity
@Table(name = "sla_employee")
public class SlaEmployee {

    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @Column(name = "name")
    @Size(max = 255)
    private String name;

    @Column(name = "surname")
    @Size(max = 255)
    private String surname;

    @Column(name = "id_number")
    @Size(max = 255)
    private String idNumber;

    @Column(name = "phone")
    @Size(max = 255)
    private String phone;

    @Column(name = "foreign_languages")
    @Size(max = 255)
    private String foreignLanguages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_job", nullable = false)
    private SlaJob job;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaRent> rents;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SlaSalary> salaries;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<SlaUser> user;

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getForeignLanguages() {
        return foreignLanguages;
    }

    public void setForeignLanguages(String foreignLanguages) {
        this.foreignLanguages = foreignLanguages;
    }

    public SlaJob getJob() {
        return job;
    }

    public void setJob(SlaJob job) {
        this.job = job;
    }

    public List<SlaRent> getRents() {
        return rents;
    }

    public void setRents(List<SlaRent> rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        return "SlaEmployee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", foreignLanguages='" + foreignLanguages + '\'' +
                '}';
    }


    public List<SlaSalary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<SlaSalary> salaries) {
        this.salaries = salaries;
    }


    public List<SlaUser> getUser() {
        return user;
    }

    public void setUser(List<SlaUser> user) {
        this.user = user;
    }
}
