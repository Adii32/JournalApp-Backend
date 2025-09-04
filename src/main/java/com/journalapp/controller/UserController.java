package com.journalapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.api.response.WeatherResponse;
import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repo.UserRepo;
import com.journalapp.service.JournalService;
import com.journalapp.service.UserService;
import com.journalapp.service.WeatherService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	@Autowired
	private WeatherService weatherService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	private UserRepo userRepo;

private Map<Long,User> entry = new HashMap<>();
@GetMapping
public ResponseEntity<List<User>> getAllEntry(){
return ResponseEntity.ok(userService.getAll());
}

@GetMapping("id/{id}")
public User getById(@PathVariable Long id) {
	return userService.getById(id);
}
@DeleteMapping()
public boolean deleteUser() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	userRepo.deleteByUserName(authentication.getName());
return true;
}
@PutMapping
public User updateUser(@RequestBody User user) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String username = authentication.getName();
return userService.updateUser(username, user);
}
@GetMapping("/get_greeting")
public ResponseEntity<?> greeting() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	WeatherResponse response = weatherService.getWeather("mumbai");
int weather =0;
	if(response != null) {
	 weather = response.getCurrent().getFeelslike();
	}
	
return new ResponseEntity<>("hii weather is ..."+weather,HttpStatus.OK);
}
}
