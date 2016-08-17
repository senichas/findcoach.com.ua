package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    @Value("${clientapi.url:false}")
    private Boolean developerMode;

    public Boolean getDeveloperMode() {
        return developerMode;
    }

    public void setDeveloperMode(Boolean developerMode) {
        this.developerMode = developerMode;
    }
}
