package ua.com.findcoach.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.com.findcoach.securiy.UserAuthenticationProvider;

/**
 * Created by senich on 10/2/2015.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/profile/email").permitAll()
                .antMatchers("/profile/email/**").permitAll()
                .antMatchers("/profile/coach/**").hasAuthority("COACH")
                .and()
                .formLogin().permitAll(); // #5
    }

    @Bean
    public UserAuthenticationProvider userAuthenticationProvider() {
        return new UserAuthenticationProvider();
    }

}
