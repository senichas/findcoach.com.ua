package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.repository.CoachRepository;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> findAllCoach() {
        return coachRepository.findAll();
    }

    public int updateCoachProfileAttributes(String alias, String title, String description) {
        Coach current = (Coach) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return coachRepository.updateCoachProfileAttributes(alias, title, description, current.getEmail());
    }

    public Coach receiveCoachProfileAttributes() {
        Coach current = (Coach) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return coachRepository.receiveCoachProfileAttributes(current.getEmail());
    }

    public int updateStatus(CoachStatus coachStatus) {
        Coach current = (Coach) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return coachRepository.updateCoachStatus(coachStatus, current.getEmail());
    }

    public Enum receiveCurrentCoachStatus() {
        Coach current = (Coach) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return coachRepository.receiveCurrentCoachStatus(current.getEmail());
    }

    public Coach findByEmail(String email) {
        Coach user = coachRepository.findByEmail(email);
        return user;
    }

    public Coach findByCoachId(Integer coachId) {
        Coach user = coachRepository.findByCoachId(coachId);
        return user;
    }

    public Coach retrieveCurrentCoach() {
        String email = ((Coach) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return coachRepository.findByEmail(email);
    }

}
