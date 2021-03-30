package pharmacyhub.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService {
	
    @Autowired
	private JavaMailSender javaMailSender;
	
	public void sendActivationCode(String email, String activationCode) throws MessagingException {
				
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String emailContent = "Thank you for your registration. In order to use your account, you'll need to activate it. <br><br>Click <a href='http://localhost:8081/register/activate/" + activationCode + "'>here</a> to activate your account.";

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(email);
		helper.setSubject("Activate your account");
		helper.setText(emailContent, true);

		javaMailSender.send(message);
	}
}
