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
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
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
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/findcoach/coach/**").hasAuthority("COACH")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

        http.addFilterAfter(customSpringSecurityFilterChain(), BasicAuthenticationFilter.class);
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

    @Bean
    public FilterChainProxy customSpringSecurityFilterChain() {
        List<SecurityFilterChain> securityFilterChains = new ArrayList<SecurityFilterChain>();
        securityFilterChains.add(new DefaultSecurityFilterChain(
                new RegexRequestMatcher(CoachUrlAliasFilter.COACH_URL_PADAWAN_MANAGEMENT, RequestMethod.GET.name()),
                coachUrlAliasFilter()));
        return new FilterChainProxy(securityFilterChains);

    }

    @Bean
    public CoachUrlAliasFilter coachUrlAliasFilter() {
        return new CoachUrlAliasFilter();
    }
}
