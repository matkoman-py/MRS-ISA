package pharmacyhub.services;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Offer;
import pharmacyhub.domain.OrderStock;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.Subscription;
import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.complaints.Reply;
import pharmacyhub.domain.enums.OfferStatus;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.CreateNewPriceForDrugDto;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.SubscriptionRepository;

@Service
public class UserNotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private DrugstoreRepository drugstoreRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Async
	public void sendActivationCode(String email, String activationCode) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String emailContent = "Thank you for your registration. In order to use your account, you'll need to activate it. <br><br>Click <a href='http://localhost:8081/register/activate/"
				+ activationCode + "'>here</a> to activate your account.";

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
	public void sendReservationConfirmation(String email, String type) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		String emailContent = "You have succesfully reserved a" + type + " appointment";

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(email);
		helper.setSubject("Account details");
		helper.setText(emailContent, true);

		javaMailSender.send(message);
	}

	@Async
	public void sendReservationConfirmationDrug(String email, String confirmationCode) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		String emailContent = "You have succesfully reserved a drug! <br> Show this code to your pharmacist to claim your drug "
				+ confirmationCode;

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(email);
		helper.setSubject("Account details");
		helper.setText(emailContent, true);

		javaMailSender.send(message);
	}

	@Async
	public void notifySubscribers(CreateNewPriceForDrugDto newPromotion) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		Drugstore drugstore = drugstoreRepository.findById(newPromotion.getDrugStoreId()).orElse(null);
		String emailContent = "There is a new promotion for drug: " + newPromotion.getDrugName() + " in '"
				+ drugstore.getName() + "' drugstore!\n";
		emailContent += "Hurry up, because this special offer is active from " + newPromotion.getStartDate() + " to "
				+ newPromotion.getEndDate() + "!\n";
		emailContent += "As you already know, you can find us at " + drugstore.getLocation().getAddress() + " in "
				+ drugstore.getLocation().getCity() + ", " + drugstore.getLocation().getCountry()
				+ ". Can't wait to see you!";
		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setSubject("Sales in " + drugstore.getName() + "!");
		helper.setText(emailContent, true);

		List<Subscription> subscriptions = subscriptionRepository.findByDrugstore(drugstore);
		for (Subscription s : subscriptions) {
			String patientEmail = s.getPatient().getEmail();
			helper.setTo(patientEmail);
			javaMailSender.send(message);
		}

	}
	
	@Async
	public void sendPickUpConfirmation(String email,String drug, String date) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		String emailContent = "You have succesfully picked up "+ drug+" at: "+ date +"!";

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(email);
		helper.setSubject("Drug pickup confirmation");
    helper.setText(emailContent, true);
		javaMailSender.send(message);
  }
  
  @Async
	public void notifySupplier(Offer offer) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String emailContent;
		String orderContent = "";
		for (OrderStock stock : offer.getDrugOrder().getStock()) {
			orderContent += "Drug:		" + stock.getDrug().getName() + "	Amount:		" + stock.getAmount() + "\n\t";
		}
		if (offer.getStatus() == OfferStatus.Accepted) {
			String address =  offer.getDrugOrder().getDrugstore().getLocation().getAddress() + ", " +  offer.getDrugOrder().getDrugstore().getLocation().getCity() + ", " +  offer.getDrugOrder().getDrugstore().getLocation().getCountry();
			emailContent = "Dear " + offer.getSupplier().getName() + ",\nYour offer for order " + orderContent + "\n\t is accepted! Please deliver " +
			" the shipement by " + offer.getDeliveryDate() + ", at " + offer.getDeliveryTime() + " to " + address + ".\nAll the best, your '" + offer.getDrugOrder().getDrugstore().getName() + "'!";
		} else {
			emailContent = "Dear " + offer.getSupplier().getName() + ",\nUnfortunately, your offer for order \n\t" + orderContent + "\n\t is declined...\\nAll the best, your '" + offer.getDrugOrder().getDrugstore().getName() + "'!";
		}

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(offer.getSupplier().getEmail());
		helper.setSubject("Offer for order in '" + offer.getDrugOrder().getDrugstore().getName() + "'");
		helper.setText(emailContent, true);

		javaMailSender.send(message);
	}
  @Async
  public void notifyPatientAboutCancelation(PharmacistAppointment appointment) throws MessagingException {
	  	MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		String emailContent = "Dear customer,\nUnfortunately we have to notify you about cancelation of your appointment with pharmacist " + appointment.getPharmacist().getName() + " " + appointment.getPharmacist().getSurname() + 
				" scheduled on " + appointment.getDate() + " due to absence of pharmacist in that period. You can still schedule an appointment with some of other pharmacists from our pharmacy!\nThank you for understanding, your " + appointment.getPharmacist().getDrugstore().getName() + "!";
		
		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(appointment.getPatient().getEmail());
		helper.setSubject("Cancelation of your appointment with pharmacist at '" + appointment.getPharmacist().getDrugstore().getName() + "' pharmacy.");
		helper.setText(emailContent, true);

		javaMailSender.send(message);
}
  @Async
public void notifyPharmacistAboutApproving(AbsenceRequest request) throws MessagingException {
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper;
	
	String emailContent = "Dear " + request.getEmployee().getName() + ",\nWe inform you that your absence request from " + request.getStartDate() + " to " + request.getEndDate() + " is approved!\nAll the best, your " + ((Pharmacist)request.getEmployee()).getDrugstore().getName() + "!";
	
	helper = new MimeMessageHelper(message, true);
	helper.setFrom("notification@pharmacyhub.com");
	helper.setTo(request.getEmployee().getEmail());
	helper.setSubject("Status of your absence request changed.");
	helper.setText(emailContent, true);

	javaMailSender.send(message);
}

@Async
public void notifyAboutRejection(AbsenceRequest request) throws MessagingException {
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper;

	String emailContent = "Dear " + request.getEmployee().getName()
			+ ",\nUnfortunately, we have to inform you that your absence request from " + request.getStartDate()
			+ " to " + request.getEndDate() + " is rejected.\nReason for that is: '" + request.getAdminComment()
			+ "' \nAll the best!";

	helper = new MimeMessageHelper(message, true);
	helper.setFrom("notification@pharmacyhub.com");
	helper.setTo(request.getEmployee().getEmail());
	helper.setSubject("Status of your absence request changed.");
	helper.setText(emailContent, true);

	javaMailSender.send(message);
}
  @Async
public void notifyPatientAboutCancelation(DermatologistAppointment appointment) throws MessagingException {
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper;
	
	String emailContent = "Dear customer,\nUnfortunately we have to notify you about cancelation of your appointment with pharmacist " + appointment.getDermatologist().getName() + " " + appointment.getDermatologist().getSurname() + 
			" scheduled on " + appointment.getDate() + " due to absence of pharmacist in that period. You can still schedule an appointment with some of other pharmacists from our pharmacy!\nThank you for understanding!";
	
	helper = new MimeMessageHelper(message, true);
	helper.setFrom("notification@pharmacyhub.com");
	helper.setTo(appointment.getPatient().getEmail());
	helper.setSubject("Cancelation of your appointment with dermatologist.");
	helper.setText(emailContent, true);

	javaMailSender.send(message);
}
  @Async
public void notifyDermatologistAboutApproving(AbsenceRequest request) throws MessagingException {
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper;
	
	String emailContent = "Dear " + request.getEmployee().getName() + ",\nWe inform you that your absence request from " + request.getStartDate() + " to " + request.getEndDate() + " is approved!\nAll the best!";
	
	helper = new MimeMessageHelper(message, true);
	helper.setFrom("notification@pharmacyhub.com");
	helper.setTo(request.getEmployee().getEmail());
	helper.setSubject("Status of your absence request changed.");
	helper.setText(emailContent, true);

	javaMailSender.send(message);
}
  	@Async
	public void notifyAboutComplaintReply(Complaint complaint, Reply reply) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		String emailContent = "Dear " + complaint.getPatient().getName() +
				",\nA system admin replied to your complaint\n" + reply.getText() + "\n";
		
		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@pharmacyhub.com");
		helper.setTo(complaint.getPatient().getEmail());
		helper.setSubject("Reply to complaint.");
		helper.setText(emailContent, true);
	
		javaMailSender.send(message);
	}
  
}
