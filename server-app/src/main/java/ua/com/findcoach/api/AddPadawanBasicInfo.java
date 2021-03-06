package ua.com.findcoach.api;

import ua.com.findcoach.domain.Gender;
import ua.com.findcoach.domain.Goal;

import java.util.Date;

public class AddPadawanBasicInfo {
    private PadawanData padawanData;
    private PadawanMeasurement padawanMeasurement;
    private PadawanProgram padawanProgram;

    public AddPadawanBasicInfo() {
        padawanData = new PadawanData();
        padawanMeasurement = new PadawanMeasurement();
        padawanProgram = new PadawanProgram();
    }

    public PadawanData getPadawanData() {
        return padawanData;
    }

    public void setPadawanData(PadawanData padawanData) {
        this.padawanData = padawanData;
    }

    public PadawanMeasurement getPadawanMeasurement() {
        return padawanMeasurement;
    }

    public void setPadawanMeasurement(PadawanMeasurement padawanMeasurement) {
        this.padawanMeasurement = padawanMeasurement;
    }

    public PadawanProgram getPadawanProgram() {
        return padawanProgram;
    }

    public void setPadawanProgram(PadawanProgram padawanProgram) {
        this.padawanProgram = padawanProgram;
    }

    public static class PadawanData {
        private String name;
        private String email;
        private Gender gender;
        private Integer year;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }
    }

    public static class PadawanMeasurement {
        private Short height;
        private float weight;
        private Short fatPercentage;

        public Short getHeight() {
            return height;
        }

        public void setHeight(Short height) {
            this.height = height;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public Short getFatPercentage() {
            return fatPercentage;
        }

        public void setFatPercentage(Short fatPercentage) {
            this.fatPercentage = fatPercentage;
        }
    }

    public static class PadawanProgram {
        private String name;
        private Goal goal;
        private String notes;
        private Date startDate;
        private Date endDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Goal getGoal() {
            return goal;
        }

        public void setGoal(Goal goal) {
            this.goal = goal;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }
}

