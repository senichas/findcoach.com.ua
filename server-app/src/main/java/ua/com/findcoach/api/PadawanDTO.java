package ua.com.findcoach.api;

import ua.com.findcoach.domain.Gender;
import ua.com.findcoach.domain.Goal;

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
    private List<PadawanProgramDTO> padawanProgramDTOList;


    public PadawanDTO(Integer padawanId, String firstName, String lastName, String email, Gender gender) {
        this.padawanId = padawanId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        padawanProgramDTOList = new ArrayList<PadawanProgramDTO>();
    }

    public class PadawanProgramDTO{
        private String programName;
        private Goal goal;
        private Integer programId;
        private String programStartDate;
        private String programFinishDate;
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        public PadawanProgramDTO(String programName, Goal goal, Integer programId, Date programStartDate, Date programFinishDate) {
            this.programName = programName;
            this.goal = goal;
            this.programId = programId;
            this.programStartDate = simpleDateFormat.format(programStartDate);
            this.programFinishDate = simpleDateFormat.format(programFinishDate);
        }

        public String getProgramStartDate() {
            return programStartDate;
        }

        public void setProgramStartDate(String programStartDate) {
            this.programStartDate = programStartDate;
        }

        public String getProgramFinishDate() {
            return programFinishDate;
        }

        public void setProgramFinishDate(String programFinishDate) {
            this.programFinishDate = programFinishDate;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public Goal getGoal() {
            return goal;
        }

        public void setGoal(Goal goal) {
            this.goal = goal;
        }

        public Integer getProgramId() {
            return programId;
        }

        public void setProgramId(Integer programId) {
            this.programId = programId;
        }
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

    public List<PadawanProgramDTO> getPadawanProgramDTOList() {
        return padawanProgramDTOList;
    }

    public void setPadawanProgramDTOList(List<PadawanProgramDTO> padawanProgramDTOList) {
        this.padawanProgramDTOList = padawanProgramDTOList;
    }
}
