package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.repository.PadawanRepository;

@Service
public class PadawanService {
    @Autowired
    private PadawanRepository padawanRepository;

    public Padawan save(Padawan padawan) {
        return padawanRepository.save(padawan);
    }
}
