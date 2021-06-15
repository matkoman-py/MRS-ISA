package pharmacyhub.dto.auth;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

public class UserDto {
	
	private String id;
	private String email;
	public Location location;
	private UserType type;
	private boolean firstLogin;
	
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

}
