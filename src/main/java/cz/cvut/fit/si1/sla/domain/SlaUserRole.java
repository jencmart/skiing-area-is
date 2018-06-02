package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;

/**
 * Represents role of user in the database
 */
@Entity
@Table(name = "sla_user_role")
public class SlaUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_role")
    private Long idUserRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", nullable = false)
    private SlaRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private SlaUser user;

    public Long getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Long idUserRole) {
        this.idUserRole = idUserRole;
    }

    public SlaRole getRole() {
        return role;
    }

    public void setRole(SlaRole role) {
        this.role = role;
    }

    public SlaUser getUser() {
        return user;
    }

    public void setUser(SlaUser user) {
        this.user = user;
    }
}
