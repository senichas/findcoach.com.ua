package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.repository.CoachRepository;

import java.util.List;

/**
 * Created by DENIS on 14.10.2015.
 */
@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> findAllCoach() {
        return coachRepository.findAll();
    }


    public int saveStatus(CoachStatus coachStatus, String email) {
        return coachRepository.setNewStatus(coachStatus, email);
    }

    public boolean isCoach() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> autorities = (List) authentication.getAuthorities();
        for (GrantedAuthority authority : autorities) {
            if (authority.getAuthority() == "COACH") ;
            return true;
        }
        return false;
    }

}
