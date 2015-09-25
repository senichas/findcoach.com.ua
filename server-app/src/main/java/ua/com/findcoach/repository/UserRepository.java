package ua.com.findcoach.repository;

import org.springframework.stereotype.Repository;
import ua.com.findcoach.domain.User;

import java.util.List;

/**
 * Created by DENIS on 24.09.2015.
 */
public interface UserRepository extends Repository {
    List<User> findByEmail(String email);
}
