package com.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.service.PasswordResetService;
import com.journalapp.service.UserService;

@RequestMapping("/auth")
@RestController
public class AuthController {
	@Autowired
	private PasswordResetService passwordService;
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam String email){
		passwordService.generateResetToken(email);
		return ResponseEntity.ok("password reset token has been sent to your email");
		
	}
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String token,@RequestParam String newPassword)
	{
	passwordService.resetPassword(token, newPassword);
	return ResponseEntity.ok("password reset successful!");
	}

}
