package ua.com.findcoach.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by senich on 10/1/2015.
 */
@Component
public class LocalizedMessageResolver {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String alias) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(alias, null, locale);
        return message;
    }

}
