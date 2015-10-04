package ua.com.findcoach.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.com.findcoach.securiy.UserAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senich on 10/2/2015.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/profile/email");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() //HTTP with Disable CSRF
                .authorizeRequests() //Authorize Request Configuration
                    .antMatchers("/profile/coach.html").hasAuthority("COACH")
                    .antMatchers("/profile/coach/**").hasAuthority("COACH")
                    .anyRequest().permitAll()
                .and() //Login Form configuration for all others
                .formLogin()
                    .loginPage("/login").permitAll()
                .and() //Logout Form configuration
                .logout().permitAll();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(userAuthenticationProvider());
        AuthenticationManager authenticationManager = new ProviderManager(providers);
        return authenticationManager;
    }
    @Bean
    public UserAuthenticationProvider userAuthenticationProvider() {
        return new UserAuthenticationProvider();
    }

}
