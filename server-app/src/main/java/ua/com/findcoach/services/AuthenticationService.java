package ua.com.findcoach.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.FacebookAuthRequestDto;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.FacebookUserToken;
import ua.com.findcoach.security.FindCoachAuthenticationProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class AuthenticationService {
    private final static String COACH_REDIRECT = "/findcoach/coach/profile/dashboard.html";
    private final static String PADAWAN_REDIRECT = "/findcoach/padawan/profile/home.html";
    private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    @Autowired
    private FindCoachAuthenticationProvider authenticationProvider;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private CoachService coachService;

    @Autowired
    Environment environment;

    public Boolean authenticateUser(FacebookAuthRequestDto authRequestDto, HttpServletRequest request) {
        Coach userProfile = coachService.findByEmail(authRequestDto.getEmail());
        if (userProfile == null) {
            //TO DO: to handle situation when there is no coach with facebook user email and redirect to page for creation such data
            return false;
        }

        String longLivedFacebookToken = getLongLivedFacebookToken(authRequestDto);
        FacebookUserToken userToken = facebookService.findUserTokenByFacebookUserId(authRequestDto.getUserId());

        if (userToken == null) {
            userToken = new FacebookUserToken();
            userToken.setLongLivedToken(longLivedFacebookToken);

            userToken.setCoachId(userProfile.getCoachId());
            userToken.setFacebookUserId(authRequestDto.getUserId());
            facebookService.save(userToken);
        } else {
            facebookService.updateTokenByUserId(longLivedFacebookToken, authRequestDto.getUserId());
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userToken.getCoachId(), "");
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(token);
        } catch (AuthenticationException e) {
            return false;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT, securityContext);

        return true;
    }

    public String getLongLivedFacebookToken(FacebookAuthRequestDto facebookDto) {
        String url = environment.getProperty("facebook.graph.url")
            .concat("oauth/access_token?grant_type=fb_exchange_token&client_id=")
            .concat(facebookDto.getApplicationId().toString())
            .concat("&client_secret=")
            .concat(environment.getProperty("facebook.application.secret"))
            .concat("&fb_exchange_token=")
            .concat(facebookDto.getShortLivedToken());
        String token;
        try {
            HttpResponse longLivedLoadResponse = doRequest(url);
            String tokenData = EntityUtils.toString(longLivedLoadResponse.getEntity(), "UTF-8");
            token = getAccessToken(tokenData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    private HttpResponse doRequest(String uri) throws IOException {
        HttpUriRequest httpRequest = new HttpGet(uri);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpRequest);
        return response;
    }

    private String getAccessToken(String data) {
        String token = "access_token=";
        String expires = "&expires";

        int startIndex = data.indexOf(token);
        int endIndex = data.indexOf(expires);

        if (startIndex != -1 && endIndex != -1) {
            return data.substring(startIndex + token.length(), endIndex);
        }
        return null;
    }

    public String getHomeLink() {
        return COACH_REDIRECT;
    }

    public Coach getCurrentUserToken() {
        Object currentUserToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUserToken == null || currentUserToken instanceof String)
            return null;
        return (Coach) currentUserToken;
    }

    public Boolean isCurrentUserTokenAlive() {
        Coach currentUser = getCurrentUserToken();

        if (currentUser == null)
            return false;

        String userData = null;
        try {
            userData = getFacebookUserDetails(currentUser.getCoachId());
        } catch (Exception e) {
        }

        return userData != null;
    }

    public String getFacebookUserDetails(Integer userId) throws IOException {
        String details;
        FacebookUserToken currentToken = facebookService.findUserTokenByCoachId(userId);
        String url = environment.getProperty("facebook.graph.url")
            .concat(currentToken.getFacebookUserId().toString())
            .concat("?access_token=")
            .concat(currentToken.getLongLivedToken());
        try {
            HttpResponse data = doRequest(url);
            details = EntityUtils.toString(data.getEntity(), "UTF-8");
        } catch (IOException e) {
            throw e;
        }
        return details;
    }
}
