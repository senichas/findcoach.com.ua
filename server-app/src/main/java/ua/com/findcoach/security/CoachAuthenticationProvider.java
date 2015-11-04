package ua.com.findcoach.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.UserRole;
import ua.com.findcoach.services.CoachService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by senich on 10/2/2015.
 */
public class CoachAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CoachService coachService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        char[] password = String.valueOf("").toCharArray();

        Coach coach = coachService.findByEmail(email);
        if (coach == null) {
            throw new BadCredentialsException("Username not found.");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.COACH.name()));

        return new UsernamePasswordAuthenticationToken(coach, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}