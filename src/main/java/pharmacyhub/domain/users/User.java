package pharmacyhub.domain.users;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pharmacyhub.domain.BaseEntity;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class User extends BaseEntity{
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	//@Column(nullable = false)
	@Column
	private String phoneNumber;
	
	@Column
	private String workingHoursFrom;
	@Column
	private String workingHoursTo;
	
	//treba nullable false mozda?
	@ManyToOne
	@JoinColumn(name="location_fk")
	public Location location;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
	private UserType type;
	
	@ManyToOne
	@JoinColumn(name="drugstore_fk")
	public Drugstore drugstore;
	
	public User() {
		
	}
	
	public User(String id, String email, String password, String name, String surname, String phoneNumber, Location location,
			UserType type) {
		this(email, password, name, surname, phoneNumber, location, type, null, null);
		this.id = id;
		
	}

	public User(String email, String password, String name, String surname, String phoneNumber, Location location,
			UserType type, String workingHoursFrom, String workingHoursTo) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.type = type;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
	public String getWorkingHoursFrom() {
		return workingHoursFrom;
	}

	public void setWorkingHoursFrom(String workingHoursFrom) {
		this.workingHoursFrom = workingHoursFrom;
	}

	public String getWorkingHoursTo() {
		return workingHoursTo;
	}

	public void setWorkingHoursTo(String workingHoursTo) {
		this.workingHoursTo = workingHoursTo;
	}
	
}
