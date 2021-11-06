package pl.yuro.springsandbox.service;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.yuro.springsandbox.entity.User;
import pl.yuro.springsandbox.entity.VerificationToken;
import pl.yuro.springsandbox.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {

	private final VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
		this.verificationTokenRepository=verificationTokenRepository;
	}
	
	@Transactional
	public VerificationToken findByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}
	
	@Transactional
	public VerificationToken findByUser(User user) {
		return verificationTokenRepository.findByUser(user);
	}
	
	@Transactional
	public void save(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(token,user);
		verificationToken.setExpireTime(expireDateCalculate(60*24));
		verificationTokenRepository.save(verificationToken);
	}
	
	private Timestamp expireDateCalculate(int expireTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, expireTimeInMinutes);
		return new Timestamp(cal.getTime().getTime());
	}
}
