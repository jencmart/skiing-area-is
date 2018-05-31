package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sla_role")
public class SlaRole {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaUserRole> usersRoles;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SlaUserRole> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(List<SlaUserRole> usersRoles) {
        this.usersRoles = usersRoles;
    }
}
