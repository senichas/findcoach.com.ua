package ua.com.findcoach.utils;

import ua.com.findcoach.domain.CoachStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DENIS on 11.10.2015.
 */
public class StatusHolder {
    Map<Enum,String> messegerHolder = new HashMap<>();

    public Map<Enum,String> getStatusMap(){
        for(CoachStatus status : CoachStatus.values()){
            messegerHolder.put(status,"coach.status."+status.getName());
        }
        return messegerHolder;
    }
}
