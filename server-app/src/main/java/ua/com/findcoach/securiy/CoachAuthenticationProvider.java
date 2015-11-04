package ua.com.findcoach.securiy;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ua.com.findcoach.domain.User;
import ua.com.findcoach.domain.UserRole;
import ua.com.findcoach.services.UserService;

/**
 * Created by senich on 10/2/2015.
 */
public class CoachAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        char[] password = String.valueOf("").toCharArray();

        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getIsCoach().equals(1)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.COACH.name()));
        }
        if (user.getIsPadawan().equals(1)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.PADAWAN.name()));
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}