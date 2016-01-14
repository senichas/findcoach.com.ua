package ua.com.findcoach.api;

import ua.com.findcoach.domain.Gender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PadawanDTO {
    private Integer padawanId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String birthday;
    private List<ProgramDTO> padawanProgramDTOList;


    public PadawanDTO(Integer padawanId, String firstName, String lastName, String email, Gender gender, Date birthday) {
        this.padawanId = padawanId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = new SimpleDateFormat("MM/dd/yy").format(birthday);
        padawanProgramDTOList = new ArrayList<ProgramDTO>();
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

    public List<ProgramDTO> getPadawanProgramDTOList() {
        return padawanProgramDTOList;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPadawanProgramDTOList(List<ProgramDTO> padawanProgramDTOList) {
        this.padawanProgramDTOList = padawanProgramDTOList;
    }
}
