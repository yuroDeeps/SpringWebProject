package pl.yuro.springsandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.yuro.springsandbox.entity.User;
import pl.yuro.springsandbox.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
	
	VerificationToken findByUser(User user);
}
