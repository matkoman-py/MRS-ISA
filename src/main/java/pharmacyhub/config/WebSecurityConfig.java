package pharmacyhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import pharmacyhub.security.RestAuthenticationEntryPoint;
import pharmacyhub.security.TokenAuthenticationFilter;
import pharmacyhub.security.TokenUtils;
import pharmacyhub.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(jwtUserDetailsService) 
			.passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Bean
	public CorsFilter corsFilter() {
	  UrlBasedCorsConfigurationSource source = new 
	  UrlBasedCorsConfigurationSource();
	  CorsConfiguration config = new CorsConfiguration();
	  config.setAllowCredentials(false);
	  config.addAllowedOrigin("*");
	  config.addAllowedHeader("*");
	  config.addAllowedMethod("*");
	  source.registerCorsConfiguration("/**", config);
	  return new CorsFilter(source);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	    
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.antMatchers(HttpMethod.GET, "/register/activate/**").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		.antMatchers(HttpMethod.POST, "/drugs/search/**").permitAll()
		.antMatchers(HttpMethod.POST, "/drugstores/search/**").permitAll()
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.antMatchers(HttpMethod.POST, "/employees/pharmacist").permitAll()//.hasAuthority("DrugstoreAdmin") // dodato
		.anyRequest().authenticated().and()
	    .cors()
	    .and().addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class)
	    .csrf().disable();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		 
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}
	
}