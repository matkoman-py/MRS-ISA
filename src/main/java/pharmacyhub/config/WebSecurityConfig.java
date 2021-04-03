package pharmacyhub.config;

import java.util.Arrays;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.cors().configurationSource(request ->
	    {
	    	CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
	    	corsConfig.setAllowedMethods(Arrays.asList("*"));
	    	corsConfig.setAllowedHeaders(Arrays.asList("*"));
	    	corsConfig.setAllowCredentials(false);
	    	return corsConfig;
	    }
	    ).and().csrf().disable();
		
	}
	
}