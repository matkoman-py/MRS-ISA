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

	@Value("pharmacyhub")
	private String APP_NAME;

	@Value("somesecret")
	public String SECRET;

	@Value("360000")
	private int EXPIRES_IN;

	@Value("Authorization")
	private String AUTH_HEADER;

	private static final String AUDIENCE_WEB = "web";

	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

	public String generateToken(String username) {
		return Jwts.builder()
				.setIssuer(APP_NAME)
				.setAudience(AUDIENCE_WEB)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SIGNATURE_ALGORITHM, SECRET)
				.compact();
	}

	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}

	public String getToken(HttpServletRequest request) {

		String authHeader = request.getHeader(AUTH_HEADER);
		
		System.out.println(request.getMethod());
		System.out.println(authHeader);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			System.out.println("pusim kurac");
			return authHeader.substring(7);
		}

		return null;
	}
	
	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}
	
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
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
