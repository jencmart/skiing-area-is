package cz.cvut.fit.si1.sla.security;

import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;


    /**
     * Performs authentication with the same contract as AuthenticationManager.authenticate(Authentication) .
     *
     * @param authentication - the authentication request object
     * @return a fully authenticated object including credentials. May return null if the AuthenticationProvider
     * is unable to support authentication of the passed Authentication object.
     * In such a case, the next AuthenticationProvider that supports the presented Authentication class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        SlaUser user = userService.loadUserByUsername(token.getName());

        if (user.getPassword() == token.getCredentials().toString()) {
            throw new BadCredentialsException("Spatne udaje");
        }

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
