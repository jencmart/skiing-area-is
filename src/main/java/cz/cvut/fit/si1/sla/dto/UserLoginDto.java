package cz.cvut.fit.si1.sla.dto;

import javax.validation.constraints.NotNull;

/**
 * Data transfer object for user login
 */
public class UserLoginDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
