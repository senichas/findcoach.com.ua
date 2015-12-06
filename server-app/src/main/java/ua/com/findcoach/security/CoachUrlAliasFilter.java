package ua.com.findcoach.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.findcoach.services.CoachService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoachUrlAliasFilter extends OncePerRequestFilter {

    public static final String COACH_URL_PADAWAN_MANAGEMENT = "^/*[a-z]*/coach/([a-zA-Z0-9_-]+)/padawan-management/.+$";
    public static final String COACH_URL_ADD_PADAWAN_WIZARD = "^/*[a-z]*/coach/([a-zA-Z0-9_-]+)/add-padawan-wizard/.+$";
    public static final String COACH_URL_PROGRAM = "^/*[a-z]*/coach/([a-zA-Z0-9_-]+)/program/.+$";

    private static final String COACH_ALIAS_EXTRACT =  "^/*[a-z]*/coach/([a-zA-Z0-9_-]+)/.+$";

    @Autowired
    private CoachService coachService;

    private static final Logger LOG = LoggerFactory.getLogger(CoachUrlAliasFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        Pattern pattern = Pattern.compile(COACH_ALIAS_EXTRACT);
        Matcher matcher = pattern.matcher(httpServletRequest.getPathInfo());

        matcher.find();
        String aliasInUrl = matcher.group(1);
        String aliasOfCurrentCoach = coachService.retrieveCurrentCoach().getAlias();

        LOG.debug("Parsed alias = " + aliasInUrl);
        LOG.debug("Current coach alias = " + aliasOfCurrentCoach);

        if (aliasInUrl == null
                || aliasOfCurrentCoach == null
                || !aliasInUrl.equals(aliasOfCurrentCoach)) {
            throw new ServletException("Alias provided in URL doesn't correspond to alias of logged user");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
