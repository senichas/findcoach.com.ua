package ua.com.findcoach.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.services.ProgramService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoachProgramFilter extends OncePerRequestFilter {

    public static final String COACH_URL_PROGRAM = "^/*[a-z]*/coach/([a-zA-Z0-9_-]+)/program/(\\d+).+$";

    private static final Logger LOG = LoggerFactory.getLogger(CoachUrlAliasFilter.class);

    @Autowired
    private ProgramService programService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Integer programId = 0;

        String url = request.getPathInfo();

        Pattern pattern = Pattern.compile(COACH_URL_PROGRAM);
        Matcher matcher = pattern.matcher(url);
        matcher.find();

        String coachAlias = matcher.group(1);
        String programIdString = matcher.group(2);

        if (StringUtils.isNumeric(programIdString)) {
            programId = Integer.parseInt(programIdString);
        }

        Program program = programService.findOneByProgramIdAndCoachAlias(programId, coachAlias);
        if (program == null) {
            throw new ServletException("Program with provided id doesn't belong to coach.");

        }

        filterChain.doFilter(request, response);

    }
}
