package ua.com.findcoach.repository;


import org.springframework.data.repository.Repository;
import ua.com.findcoach.domain.User;

/**
 * Created by DENIS on 24.09.2015.
 */
public interface UserRepository extends Repository<User, Integer> {
    User findByEmail(String email);
}
