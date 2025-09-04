package com.journalapp.securityConfig;

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
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	http.
	csrf(csrf->csrf.disable()).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeRequests(auth -> auth.requestMatchers("/user/**","/journal/**").authenticated().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll()).addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
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


