package usijani.momci.pharmacyhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PharmacyhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyhubApplication.class, args);
	}
	
    

    // Fix the CORS errors
    /*@Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {  
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        CorsConfiguration config = new CorsConfiguration();  
        config.setAllowCredentials(true); 
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080")); 
        config.setAllowedMethods(Collections.singletonList("*"));  
        config.setAllowedHeaders(Collections.singletonList("*"));  
        source.registerCorsConfiguration("/**", config);  
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
        return bean;  
    }*/
}	
