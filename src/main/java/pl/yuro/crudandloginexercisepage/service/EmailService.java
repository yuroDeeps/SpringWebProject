package pl.yuro.crudandloginexercisepage.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pl.yuro.crudandloginexercisepage.entity.User;
import pl.yuro.crudandloginexercisepage.entity.VerificationToken;

@Service
public class EmailService {

	private final VerificationTokenService verificationToklenService;
	private final TemplateEngine templateEngine;
	private final JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(VerificationTokenService verificationTokenService,TemplateEngine templateEngine, JavaMailSender javaMailSender) {
		this.verificationToklenService = verificationTokenService;
		this.templateEngine = templateEngine;
		this.javaMailSender = javaMailSender;
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
}
