package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Measure;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.repository.MeasureRepository;
import ua.com.findcoach.repository.PadawanRepository;

import java.util.ArrayList;

@Service
public class PadawanService {
    @Autowired
    private PadawanRepository padawanRepository;

    @Autowired
    private MeasureRepository measureRepository;

    public Padawan save(Padawan padawan) {
        return padawanRepository.save(padawan);
    }

    public Padawan saveAndFlush(Padawan padawan){ return  padawanRepository.saveAndFlush(padawan);}
    public Padawan findById(Integer padawanId){return padawanRepository.findByPadawanId(padawanId);}

    public Padawan addMeasureToPadawan(Padawan padawan, Measure measure) {
        measure.setPadawan(padawan);
        if (padawan.getMeasures() == null) {
            padawan.setMeasures(new ArrayList<>());
        }

        padawan.getMeasures().add(measure);
        return padawanRepository.save(padawan);
    }
}
