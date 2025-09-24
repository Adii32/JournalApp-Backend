package com.journalapp.securityConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.journalapp.filter.JwtFilter;
import com.journalapp.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired 
   private JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailsServiceImpl userDetails;
	@Bean

	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://localhost:4200"));
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(List.of("*"));
	    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
	    configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

@Bean

public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
	.csrf(csrf->csrf.disable()).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeRequests(auth -> auth.requestMatchers("/user/**","/journal/**","/friend/**","/like/**").authenticated().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll()).addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetails)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    
}

@Bean 
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

}


