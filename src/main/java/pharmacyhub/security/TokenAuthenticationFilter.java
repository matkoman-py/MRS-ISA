package pharmacyhub.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

public class TokenAuthenticationFilter extends OncePerRequestFilter{

	
	private TokenUtils tokenUtils;
	
	private UserDetailsService userDetailsService;
	
	protected final Log LOGGER = LogFactory.getLog(getClass());

	public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
		this.tokenUtils = tokenHelper;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String email;
		
		String authToken = tokenUtils.getToken(request);
		try {

			if (authToken != null) {
				
				// 2. Citanje korisnickog imena iz tokena
				email = tokenUtils.getUsernameFromToken(authToken);
				
				if (email != null) {
					
					// 3. Preuzimanje korisnika na osnovu username-a
					UserDetails userDetails = userDetailsService.loadUserByUsername(email);
					
					for (var a : userDetails.getAuthorities()) {
						System.out.println(a.getAuthority());
					}
					
					
					// 4. Provera da li je prosledjeni token validan
					if (tokenUtils.validateToken(authToken, userDetails)) {
						
						// 5. Kreiraj autentifikaciju
						TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
						authentication.setToken(authToken);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}
			
		}  catch (ExpiredJwtException ex){
			LOGGER.debug("Token expired!");
		}
		
		filterChain.doFilter(request, response);
	}
	
	
	
}
