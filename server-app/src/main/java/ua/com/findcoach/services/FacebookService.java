package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.findcoach.domain.FacebookUserToken;
import ua.com.findcoach.repository.FacebookUserTokenRepository;

@Service
@Transactional
public class FacebookService {

    @Autowired
    private FacebookUserTokenRepository facebookUserTokenRepository;

    public FacebookUserToken save(FacebookUserToken facebookUserToken) {
        facebookUserTokenRepository.save(facebookUserToken);
        return facebookUserToken;
    }

    public FacebookUserToken findUserTokenByFacebookUserId(Long facebookUserId) {
        return facebookUserTokenRepository.findUserTokenByFacebookUserId(facebookUserId);
    }

    public FacebookUserToken findUserTokenByCoachId(Integer coachId) {
        return facebookUserTokenRepository.findUserTokenByCoachId(coachId);
    }

    public int updateTokenByUserId(String userToken, Long userId) {
        return facebookUserTokenRepository.updateToken(userToken, userId);
    }

}
