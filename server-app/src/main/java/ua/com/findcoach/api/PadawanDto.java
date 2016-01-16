package ua.com.findcoach.api;

import ua.com.findcoach.domain.Gender;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PadawanDto {
    private Integer padawanId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private LocalDate birthday;
    private boolean padawanActive;
    private List<ProgramDto> padawanProgramDTOList;


    public PadawanDto(Integer padawanId, String firstName, String lastName, String email, Gender gender, Date birthday, boolean padawanActive) {
        this.padawanId = padawanId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.padawanActive = padawanActive;
        padawanProgramDTOList = new ArrayList<ProgramDto>();
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

    public List<ProgramDto> getPadawanProgramDTOList() {
        return padawanProgramDTOList;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean isPadawanActive() {
        return padawanActive;
    }

    public void setPadawanActive(boolean padawanActive) {
        this.padawanActive = padawanActive;
    }

    public void setPadawanProgramDTOList(List<ProgramDto> padawanProgramDTOList) {
        this.padawanProgramDTOList = padawanProgramDTOList;
    }
}
