package usijani.momci.pharmacyhub;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
