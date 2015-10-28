package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
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


    public int updateStatus(CoachStatus coachStatus) {
        return coachRepository.updateCoachStatus(coachStatus, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public Enum getCurrentStatus(){
        return coachRepository.selectCurrentStatus((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public int setCoachCV(String alias, String header, String describe){
        return coachRepository.setCoachDescription(alias,header,describe,(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
