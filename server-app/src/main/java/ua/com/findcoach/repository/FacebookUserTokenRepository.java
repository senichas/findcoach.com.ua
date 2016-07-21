package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.com.findcoach.domain.FacebookUserToken;

public interface FacebookUserTokenRepository extends CrudRepository<FacebookUserToken, Integer> {

	FacebookUserToken findUserTokenByFacebookUserId(Long facebookUserId);

	FacebookUserToken findUserTokenByCoachId(Integer coachId);

	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE FacebookUserToken t SET t.longLivedToken = :userToken WHERE t.facebookUserId = :facebookUserId")
	int updateToken(@Param("userToken") String userToken, @Param("facebookUserId") Long facebookUserId);

}
