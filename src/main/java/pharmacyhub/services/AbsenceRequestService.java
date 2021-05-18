package pharmacyhub.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.dto.AbsenceRequestDto;
import pharmacyhub.repositories.AbsenceRequestRepository;
import pharmacyhub.repositories.users.UserRepository;

@Service
public class AbsenceRequestService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AbsenceRequestRepository absenceRequestRepository;
	
	public String createNewRequest(AbsenceRequestDto createAbsenceRequestDto) throws ParseException {
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(createAbsenceRequestDto.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(createAbsenceRequestDto.getEndDate());
		AbsenceRequest ar = new AbsenceRequest(createAbsenceRequestDto.getReason(),startDate,endDate,userRepository.findById(createAbsenceRequestDto.getEmployeeId()).orElse(null));
		absenceRequestRepository.save(ar);
		//System.out.println(createAbsenceRequestDto.getEmployeeId()+createAbsenceRequestDto.getReason());
		return "Success!";
	}

}
