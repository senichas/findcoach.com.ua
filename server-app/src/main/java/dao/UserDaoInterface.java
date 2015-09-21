package dao;

import ua.com.findcoach.domain.User;

/**
 * Created by DENIS on 21.09.2015.
 */
public interface UserDaoInterface {
    public User selectByEmail (String email);
}
