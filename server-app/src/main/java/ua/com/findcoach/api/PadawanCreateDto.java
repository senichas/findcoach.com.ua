package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ua.com.findcoach.api.deserializers.DateDeserializer;
import ua.com.findcoach.domain.Gender;

import java.time.LocalDate;

public class PadawanCreateDto {
    private Integer padawanId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate birthday;
    private String notes;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
