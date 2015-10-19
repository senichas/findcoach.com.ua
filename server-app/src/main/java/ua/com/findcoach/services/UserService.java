package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.User;
import ua.com.findcoach.repository.UserRepository;
import ua.com.findcoach.securiy.UserAuthenticationProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {

    private final static String COACH_REDIRECT = "/profile/coach.html";
    private final static String PADAWAN_REDIRECT = "/profile/padawan.html";
    private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    @Autowired
    private UserRepository repository;


    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    public String calculateHomeLinkForUser(String email) {
        User user = findUserByEmail(email);
        String redirectLink = "";
        if (user != null) {
            if (user.getIsCoach() == 1) {
                redirectLink = COACH_REDIRECT;
            } else if (user.getIsPadawan() == 1) {
                redirectLink = PADAWAN_REDIRECT;
            }
        }
        return redirectLink;
    }

    public Boolean authenticateUser(String email, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, "");
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication;
        try {
            authentication = userAuthenticationProvider.authenticate(token);
        } catch (AuthenticationException e) {
            return false;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT, securityContext);

        return true;
    }

    public User findUserByEmail(String email) {
        User user = repository.findByEmail(email);
        return user;
    }

}
