package ua.com.findcoach.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.FacebookAuthRequestDto;
import ua.com.findcoach.domain.FacebookUserToken;
import ua.com.findcoach.security.FacebookAuthenticationProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class AuthenticationService {
    private final static String COACH_REDIRECT = "/findcoach/coach/profile/dashboard.html";
    private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    private static final String FACEBOOK_APP_SECRET = "7134dc709dc6282e6b35ccccf25cd2f4";
    private static final String FACEBOOK_GRAPH_URL = "https://graph.facebook.com/";

    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Autowired
    private FacebookService facebookService;

    public Boolean authenticateUser(FacebookAuthRequestDto authRequestDto, HttpServletRequest request) {
        String longLivedFacebookToken = getLongLivedFacebookToken(authRequestDto);

        FacebookUserToken userToken = facebookService.findUserTokenById(authRequestDto.getUserId());
        if (userToken == null) {
            userToken = new FacebookUserToken();
            userToken.setLongLivedToken(longLivedFacebookToken);
            userToken.setEmail(authRequestDto.getEmail());
            userToken.setUserId(authRequestDto.getUserId());
            facebookService.save(userToken);
        } else {
            facebookService.updateTokenByUserId(longLivedFacebookToken, authRequestDto.getUserId());
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequestDto.getUserId(), "");
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication;
        try {
            authentication = facebookAuthenticationProvider.authenticate(token);
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
        String url = FACEBOOK_GRAPH_URL
            .concat("oauth/access_token?grant_type=fb_exchange_token&client_id=")
            .concat(facebookDto.getApplicationId().toString())
            .concat("&client_secret=")
            .concat(FACEBOOK_APP_SECRET)
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

    public FacebookUserToken getCurrentUserToken() {
        Object currentUserToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUserToken == null || currentUserToken instanceof String)
            return null;
        return (FacebookUserToken) currentUserToken;
    }

    public Boolean isCurrentUserTokenAlive() {
        FacebookUserToken currentToken = getCurrentUserToken();

        if (currentToken == null)
            return false;

        String userData = null;
        try {
            userData = getFacebookUserDetails(currentToken);
        } catch (Exception e) {
        }

        return userData != null;
    }

    public String getFacebookUserDetails(FacebookUserToken userToken) throws IOException {
        String details;
        String url = FACEBOOK_GRAPH_URL
            .concat(userToken.getUserId().toString())
            .concat("?access_token=")
            .concat(userToken.getLongLivedToken());
        try {
            HttpResponse data = doRequest(url);
            details = EntityUtils.toString(data.getEntity(), "UTF-8");
        } catch (IOException e) {
            throw e;
        }
        return details;
    }
}
