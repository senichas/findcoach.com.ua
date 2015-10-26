package ua.com.findcoach.utils;

import ua.com.findcoach.domain.PadawanStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksander Krasilnikov on 20.10.2015.
 */
public class PadawanStatusHolder {
    private final static Map<Enum, String> MESSEGES_KEYS_HOLDER = new HashMap<>();
    static {
        for (PadawanStatus status : PadawanStatus.values()) {
            MESSEGES_KEYS_HOLDER.put(status, "padawan.status." + status.getStatus());
        }
    }

    public static Map<Enum, String> getStatusMap() {
        return MESSEGES_KEYS_HOLDER;
    }
}
