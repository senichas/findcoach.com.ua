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

public class FindCoachAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CoachService coachService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Integer coachId = (Integer) authentication.getPrincipal();
        char[] password = String.valueOf("").toCharArray();

        Coach userToken = coachService.findByCoachId(coachId);
        if (userToken == null) {
            throw new BadCredentialsException("UserId not found.");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.COACH.name()));

        return new UsernamePasswordAuthenticationToken(userToken, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
