package pharmacyhub.domain.users;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

public class Employee extends User {
	
	//TODO: add work schedule

	public Employee() {
		super();
	}

	public Employee(String email, String password, String name, String surname, String phoneNumber, Location location,
			UserType type, boolean status, String activationCode, String workingHoursFrom, String workingHoursTo) {
		super(email, password, name, surname, phoneNumber, location, type, status, activationCode, workingHoursFrom, workingHoursTo);
	}
}
