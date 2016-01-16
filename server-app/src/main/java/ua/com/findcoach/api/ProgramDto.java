package ua.com.findcoach.api;

import ua.com.findcoach.domain.Goal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DENIS on 06.01.2016.
 */
public class ProgramDto {
        private String programName;
        private Goal goal;
        private Integer programId;
        private String programStartDate;
        private String programFinishDate;
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");

        public ProgramDto(String programName, Goal goal, Integer programId, Date programStartDate, Date programFinishDate) {
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
