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
import ua.com.findcoach.security.CoachAuthenticationProvider;
import ua.com.findcoach.security.CoachUrlAliasFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/findcoach/authentication/email");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(coachUrlAliasFilter())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/findcoach/coach/**").hasAuthority("COACH")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
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
    public CoachAuthenticationProvider userAuthenticationProvider() {
        return new CoachAuthenticationProvider();
    }

    /*@Bean
    public FilterChainProxy springSecurityFilterChain() {
        List<SecurityFilterChain> securityFilterChains = new ArrayList<SecurityFilterChain>();
        securityFilterChains.add(new DefaultSecurityFilterChain(
                new RegexRequestMatcher("^/[a-z]+/coach/([a-zA-Z0-9]+)/.+$", RequestMethod.GET.name()),
                coachUrlAliasFilter()));
        return new FilterChainProxy(securityFilterChains);

    }*/

    @Bean
    public CoachUrlAliasFilter coachUrlAliasFilter() {
        return new CoachUrlAliasFilter();
    }
}
