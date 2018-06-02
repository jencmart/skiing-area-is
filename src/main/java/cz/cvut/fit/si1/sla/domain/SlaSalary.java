package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents salary of employee in the database
 */
@Entity
@Table(name = "sla_salaries")
public class SlaSalary implements Serializable {

    @Id
    @Column(name = "id_salaries")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalaries;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "from_date")
    private java.sql.Timestamp fromDate;

    @Column(name = "to_date")
    private java.sql.Timestamp toDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee", nullable = false)
    private SlaEmployee employee;

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
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

    public SlaEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(SlaEmployee employee) {
        this.employee = employee;
    }

    public Long getIdSalaries() {
        return idSalaries;
    }

    public void setIdSalaries(Long idSalaries) {
        this.idSalaries = idSalaries;
    }
}
