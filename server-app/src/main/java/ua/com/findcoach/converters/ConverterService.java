package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.PadawanDto;
import ua.com.findcoach.domain.Padawan;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterService {

    @Autowired
    private ProgramConverterService programConverterService;


    public PadawanDto convertPadawanToDto(Padawan padawan) {
        PadawanDto dto = new PadawanDto();
        dto.setPadawanId(padawan.getPadawanId());
        dto.setPadawanActive(padawan.isActive());
        dto.setFirstName(padawan.getFirstName());
        dto.setLastName(padawan.getLastName());
        dto.setBirthday(padawan.getBirthday());
        dto.setEmail(padawan.getEmail());
        dto.setGender(padawan.getGender());
        dto.setNotes(padawan.getNotes());

        dto.setProgramDtos(programConverterService.convertProgramsToDtos(padawan.getProgramList()));

        return dto;
    }

    public List<PadawanDto> convertPadawansToDtos(List<Padawan> padawans) {
        return padawans.stream().map(padawan -> convertPadawanToDto(padawan)).collect(Collectors.toList());
    }
}
