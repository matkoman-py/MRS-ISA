package pharmacyhub.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService {
	
    @Autowired
	private JavaMailSender javaMailSender;
	
    @Async
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
	
    @Async
	public void sendEmployeeInitialPassword(String email, String password) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String emailContent = "Good luck, here is your initial password, please use this to login and then change your password"
				+ "<br>password: " + password + "<br>";

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(email);
		helper.setSubject("Account details");
		helper.setText(emailContent, true);

		javaMailSender.send(message);
	}

    @Async
	public void sendReservationConfirmation(String email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String emailContent = "You have succesfully reserved a dermatologist appointment";

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(email);
		helper.setSubject("Account details");
		helper.setText(emailContent, true);

		javaMailSender.send(message);	
	}
}
