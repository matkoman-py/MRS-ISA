package pharmacyhub.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	@Value("spring-security-example")
	private String APP_NAME;

	@Value("somesecret")
	public String SECRET;

	@Value("3600000")
	private int EXPIRES_IN;

	@Value("Authorization")
	private String AUTH_HEADER;

	private static final String AUDIENCE_WEB = "web";

	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

	public String generateToken(String username) {
		return Jwts.builder().setIssuer(APP_NAME).setAudience(AUDIENCE_WEB).setSubject(username).setIssuedAt(new Date())
				.setExpiration(generateExpirationDate()).signWith(SIGNATURE_ALGORITHM, SECRET).compact();
	}

	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}

	public String getToken(HttpServletRequest request) {

		String authHeader = request.getHeader(AUTH_HEADER);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

	public String getUsernameFromToken(String token) {
		String username;

		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			username = null;
		}

		return username;
	}

	public Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			claims = null;
		}

		return claims;
	}
	
	public int getExpiresIn() {
		return EXPIRES_IN;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
//		User user = (User) userDetails;
		final String username = getUsernameFromToken(token);
//		final Date created = getAllClaimsFromToken(token).getIssuedAt();

		return (username != null && username.equals(userDetails.getUsername()));
//			&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
	}
}
