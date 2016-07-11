package ua.com.findcoach.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import ua.com.findcoach.domain.EventType;
import ua.com.findcoach.domain.Goal;
import ua.com.findcoach.utils.CoachStatusHolder;
import ua.com.findcoach.utils.EmailValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by DENIS on 26.09.2015.
 */
@Configuration
@PropertySource({"classpath:jdbc.properties"})
@ComponentScan({"ua.com.findcoach.controllers", "ua.com.findcoach.services", "ua.com.findcoach.converters"})
@EnableWebMvc
public class ApplicationConfiguration {


    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }

    @Bean
    public CoachStatusHolder statusHolder() {
        return new CoachStatusHolder();
    }

    @Bean
    public VelocityConfigurer velocityConfig() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("WEB-INF/templates/");
        Properties props = new Properties();
        props.setProperty("input.encoding", "UTF-8");
        props.setProperty("output.encoding", "UTF-8");
        velocityConfigurer.setVelocityProperties(props);
        return velocityConfigurer;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
        velocityViewResolver.setCache(false);
        velocityViewResolver.setPrefix("");
        velocityViewResolver.setSuffix(".html");
        velocityViewResolver.setContentType("text/html; charset=UTF-8");
        return velocityViewResolver;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Map<EventType, String> eventTypeLocalizationKeys() {
        Map<EventType, String> keys = new HashMap<>();
        keys.put(EventType.PUBLIC_TRAINING, "domain.public_training");
        keys.put(EventType.TRAINING, "domain.training");
        return keys;
    }

    @Bean
    public Map<Goal, String> programGoalsLocalizationKeys() {
        Map<Goal, String> keys = new HashMap<>();
        keys.put(Goal.ENDURANCE, "program.goal.endurance");
        keys.put(Goal.FAT_BURN, "program.goal.fat_burn");
        keys.put(Goal.STRENGTH, "program.goal.strength");
        return keys;
    }
}
