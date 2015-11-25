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

    public Padawan addMeasureToPadawan(Padawan padawan, Measure measure) {
        measure.setPadawan(padawan);
        Measure savedMeasure = measureRepository.save(measure);
        if (padawan.getMeasures() == null) {
            padawan.setMeasures(new ArrayList<>());
        }

        padawan.getMeasures().add(savedMeasure);
        return padawanRepository.save(padawan);
    }
}
