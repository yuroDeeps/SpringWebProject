package pl.yuro.crudandloginexercisepage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.yuro.crudandloginexercisepage.entity.User;
import pl.yuro.crudandloginexercisepage.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
	
	VerificationToken findByUser(User user);
	
}
