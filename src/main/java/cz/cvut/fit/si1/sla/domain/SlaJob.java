package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Represents job in the database
 */
@Entity
@Table(name = "sla_job")
public class SlaJob {

    @Id
    @Column(name = "id_job")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJob;

    @Column(name = "title")
    @Size(max = 255)
    private String title;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaEmployee> employees;

    public Long getIdJob() {
        return idJob;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SlaEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<SlaEmployee> employees) {
        this.employees = employees;
    }
}
