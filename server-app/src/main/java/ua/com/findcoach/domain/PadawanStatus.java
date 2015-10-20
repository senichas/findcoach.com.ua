package ua.com.findcoach.domain;

/**
 * Created by pc on 12.10.2015.
 */
public enum PadawanStatus {
        LOOKING_FOR_COACH("looking_for_coach"),
        TRAINING("traning"),
        NOT_ACTIVE("not_active");

        private final String status;

        private PadawanStatus(String s) {
                status = s;
        }

        public String getStatus() {
                return status;
        }

}
