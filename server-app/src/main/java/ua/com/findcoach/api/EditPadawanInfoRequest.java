package ua.com.findcoach.api;

import ua.com.findcoach.domain.Gender;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditPadawanInfoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private LocalDate birthday;
    private boolean active;


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

    public void setBirthday(Date birthday) {
        this.birthday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}


