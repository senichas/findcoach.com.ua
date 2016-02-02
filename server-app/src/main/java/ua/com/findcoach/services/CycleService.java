package ua.com.findcoach.services;

import ua.com.findcoach.domain.Cycle;
import ua.com.findcoach.repository.CycleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CycleService {
    @Autowired
    private CycleRepository cycleRepository;

    public Cycle save(Cycle cycle) {
        cycleRepository.save(cycle);
        return cycle;
    }

    public Cycle findCycleById(Integer cycleId) {
        return cycleRepository.findOneByCycleId(cycleId);
    }
}
