package ua.com.findcoach.utils;

import ua.com.findcoach.domain.CoachStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DENIS on 11.10.2015.
 */
public class CoachStatusHolder {
    private final static Map<Enum, String> MESSEGES_KEYS_HOLDER = new HashMap<>();
    static {
        for (CoachStatus status : CoachStatus.values()) {
            MESSEGES_KEYS_HOLDER.put(status, "coach.status." + status.getName());
        }
    }

    public  Map<Enum, String> getStatusMap() {
        return MESSEGES_KEYS_HOLDER;
    }
}
