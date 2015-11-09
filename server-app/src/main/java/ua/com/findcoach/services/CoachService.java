package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.domain.User;
import ua.com.findcoach.repository.CoachRepository;
import ua.com.findcoach.security.CoachAuthenticationProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DENIS on 14.10.2015.
 */
@Service
public class CoachService {
    private final static String COACH_REDIRECT = "/findcoach/coach/profile/dashboard.html";
    private final static String PADAWAN_REDIRECT = "/findcoach/padawan/profile/home.html";
    private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private CoachAuthenticationProvider coachAuthenticationProvider;


    public List<Coach> findAllCoach() {
        return coachRepository.findAll();
    }


    public int updateStatus(CoachStatus coachStatus) {
        return coachRepository.updateCoachStatus(coachStatus, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public Boolean authenticateUser(String email, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, "");
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication;
        try {
            authentication = coachAuthenticationProvider.authenticate(token);
        } catch (AuthenticationException e) {
            return false;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT, securityContext);

        return true;
    }

    public Coach findByEmail(String email) {
        Coach user = coachRepository.findByEmail(email);
        return user;
    }

    public String calculateHomeLink(String email) {
        User user = findByEmail(email);
        String redirectLink = "";
        if (user != null) {
            redirectLink = COACH_REDIRECT;
        }
        return redirectLink;
    }

    public Coach retrieveCurrentCoach() {
        return (Coach) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
