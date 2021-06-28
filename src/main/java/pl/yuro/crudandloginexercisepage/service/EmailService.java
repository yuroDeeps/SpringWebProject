package pl.yuro.crudandloginexercisepage.service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pl.yuro.crudandloginexercisepage.entity.User;
import pl.yuro.crudandloginexercisepage.entity.VerificationToken;
import pl.yuro.crudandloginexercisepage.repository.UserRepository;

@Service
public class EmailService {

	private final VerificationTokenService verificationToklenService;
	private final TemplateEngine templateEngine;
	private final JavaMailSender javaMailSender;
    private UserRepository userRepository;
	
	@Autowired
	public EmailService(VerificationTokenService verificationTokenService,TemplateEngine templateEngine, JavaMailSender javaMailSender, UserRepository userRepository) {
		this.verificationToklenService = verificationTokenService;
		this.templateEngine = templateEngine;
		this.javaMailSender = javaMailSender;
		this.userRepository = userRepository;
	}
	
	public void sendHTMLEmail(User user) throws MessagingException {
		VerificationToken verificationToken = verificationToklenService.findByUser(user);
		
		if(verificationToken != null) {
			String token = verificationToken.getToken();
			Context context = new Context();
			context.setVariable("title", "Verify your email adress");
			context.setVariable("link", "http:/localhost:8080/emailconfirmation?token="+token);
			
			String body = templateEngine.process("verification.html", context);
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			
			helper.setTo(user.getEmail());
			helper.setSubject("email adress verification");
			helper.setText(body,true);
			javaMailSender.send(message);
			
		}
	}
	
	@Transactional
	public void sendPasswordChange(String email) {
		User user = userRepository.getUserByEmail(email);
		if(user != null) {
			String resetToken = UUID.randomUUID().toString();			
			verificationToklenService.save(resetToken, user);
			
			Context context = new Context();
			context.setVariable("title", "Change your password");
			context.setVariable("link", "http:/localhost:8080/changePassword?token="+resetToken);
			
			String body = templateEngine.process("verification.html", context);
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(message,true);
				helper.setTo(user.getEmail());
				helper.setSubject("email adress verification");
				helper.setText(body,true);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			javaMailSender.send(message);
			
		}
		
	}
}
