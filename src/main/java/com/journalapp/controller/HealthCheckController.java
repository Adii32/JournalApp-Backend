package com.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.api.response.WeatherResponse;
import com.journalapp.entity.User;
import com.journalapp.service.UserService;
import com.journalapp.service.WeatherService;
import com.journalapp.utils.JwtUtil;

@RestController
@RequestMapping("/public")

public class HealthCheckController {
	@Autowired 
	JwtUtil jwtUtil;
	@Autowired
private AuthenticationManager authenticationManager;
@Autowired
UserDetailsService userDetailsService;
	@Autowired
private UserService userService;
@Autowired
private AuthenticationManager authenticationManger;

	@PostMapping("/signup")
	public User createEntry(@RequestBody User user) {
		return this.userService.createUser(user);
	}
		@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
	  // try{
    //         authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
    //         UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
    //         String jwt = jwtUtil.generateToken(userDetails.getUsername());
    //         return new ResponseEntity<>(jwt, HttpStatus.OK);
    //     }catch (Exception e){
         
    //         return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
    //     }
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
			String jwt = jwtUtil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(jwt,HttpStatus.OK);
		} catch (Exception e) {
	   return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
		}
	
	}
}
