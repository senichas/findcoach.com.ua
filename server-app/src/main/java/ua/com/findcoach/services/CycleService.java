package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.findcoach.domain.Cycle;
import ua.com.findcoach.repository.CycleRepository;

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

    public Cycle updateCycle(Integer cycleId, String cycleName, String cycleDescription) {
        Cycle cycle = cycleRepository.findOneByCycleId(cycleId);
        cycle.setName(cycleName);
        cycle.setNotes(cycleDescription);

        return cycleRepository.save(cycle);
    }
}
