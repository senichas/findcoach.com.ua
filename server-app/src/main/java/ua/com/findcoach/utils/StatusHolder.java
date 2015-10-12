package ua.com.findcoach.utils;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.i18n.LocalizedMessageResoler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DENIS on 11.10.2015.
 */
public class StatusHolder {
    @Autowired
    private LocalizedMessageResoler messageResoler;
    List<String> messegerHolder = new ArrayList<>();

    public List<String> getMesseger(){
        for(CoachStatus status : CoachStatus.values()){
            String statusResoler = messageResoler.getMessage("coach.status."+status.getName());
            messegerHolder.add(statusResoler);
        }
        return messegerHolder;
    }
}
