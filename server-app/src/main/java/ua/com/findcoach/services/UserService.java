package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.User;
import ua.com.findcoach.repository.UserRepository;

@Service
public class UserService {

    final static String COACH_REDIRECT = "/profile/coach.html";
    final static String PADAWAN_REDIRECT = "/profile/padawan.html}";


    @Autowired
    private UserRepository repository;

    public String calculateHomeLinkForUser(String email) {
        User user = repository.findByEmail(email);
        String redirectLink = "";
        if (user != null && user.getIsCoach() == 1) {
            redirectLink = COACH_REDIRECT;
        } else if (user != null && user.getIsPadawan() == 1) {
            redirectLink = PADAWAN_REDIRECT;
        }

        return redirectLink;
    }
}
