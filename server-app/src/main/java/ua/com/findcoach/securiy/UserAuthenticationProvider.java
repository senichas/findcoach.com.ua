package ua.com.findcoach.securiy;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.com.findcoach.domain.UserRole;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by senich on 10/2/2015.
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        char[] password = String.valueOf("").toCharArray();

        if (!"coach@test.com".equals(email)) {
            throw new BadCredentialsException("Username not found.");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.COACH.name()));

        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}