package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.domain.SlaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SlaUser user = userService.loadUserByUserame(username);

        if (user == null) {
            throw new UsernameNotFoundException("Uzivatel " + username + " nenalezen.");
        }

        return user;

    }
}
