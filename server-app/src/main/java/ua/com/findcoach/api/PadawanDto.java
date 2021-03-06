package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.findcoach.api.serializers.DateSerializer;
import ua.com.findcoach.domain.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PadawanDto {
    private Integer padawanId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    @JsonSerialize(using = DateSerializer.class)
    private LocalDate birthday;
    private boolean padawanActive;
    private String notes;
    private List<ProgramDto> programDtos;


    public PadawanDto() {
    }

    public PadawanDto(Integer padawanId, String firstName, String lastName, String email, Gender gender, LocalDate birthday, boolean padawanActive) {
        this.padawanId = padawanId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.padawanActive = padawanActive;
        programDtos = new ArrayList<ProgramDto>();
    }

    public Integer getPadawanId() {
        return padawanId;
    }

    public void setPadawanId(Integer padawanId) {
        this.padawanId = padawanId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<ProgramDto> getProgramDtos() {
        return programDtos;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isPadawanActive() {
        return padawanActive;
    }

    public void setPadawanActive(boolean padawanActive) {
        this.padawanActive = padawanActive;
    }

    public void setProgramDtos(List<ProgramDto> programDtos) {
        this.programDtos = programDtos;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
