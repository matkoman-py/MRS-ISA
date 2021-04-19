package pharmacyhub.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import pharmacyhub.domain.users.User;

public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;
    @JsonProperty("user")
    private UserDto user;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn, User user) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.user = new UserDto();
        this.user.setId(user.getId());
        this.user.setLocation(user.getLocation());
        this.user.setEmail(user.getEmail());
        this.user.setType(user.getType());
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto userDto) {
		this.user = userDto;
	}
    
}