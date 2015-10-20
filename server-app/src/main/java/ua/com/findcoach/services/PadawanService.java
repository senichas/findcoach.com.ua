package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.PadawanStatus;
import ua.com.findcoach.repository.PadawanRepository;

import java.util.List;

/**
 * Created by pc on 20.10.2015.
 */
@Service
public class PadawanService {
    @Autowired
    private PadawanRepository padawanRepository;

    public List<Padawan> findAllCoach() {
        return padawanRepository.findAll();
    }


    public int updateStatus(PadawanStatus padawanStatus) {
        return padawanRepository.updatePadawanStatus(padawanStatus, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}