package cz.cvut.fit.si1.sla.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents user in the database
 */
@Entity
@Table(name = "sla_user")
public class SlaUser implements UserDetails {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "username")
    @Size(max = 255)
    private String username;

    @Column(name = "password")
    @Size(max = 255)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer")
    private SlaCustomer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    private SlaEmployee employee;

    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SlaUserRole> userRoles;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (SlaUserRole role : this.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
        }

        return grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SlaCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SlaCustomer customer) {
        this.customer = customer;
    }

    public SlaEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(SlaEmployee employee) {
        this.employee = employee;
    }

    public List<SlaUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<SlaUserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
