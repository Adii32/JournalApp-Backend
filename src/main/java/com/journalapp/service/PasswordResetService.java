package com.journalapp.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.journalapp.entity.User;
import com.journalapp.repo.UserRepo;
@Service
public class PasswordResetService {
	@Autowired
	private UserRepo userRepo;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private EmailService emailService;
	//code to reset password
	public void generateResetToken(String email) {
	User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
	String token = UUID.randomUUID().toString();
	user.setResetToken(token);
	user.setTokenExpiry(LocalDateTime.now().plusMinutes(5));

	userRepo.save(user);
	emailService.sendEmail(user.getEmail(),"password reset token","hese is your password reset token(valid only for 15 min)"+token);


	}
	//reset password
	public void resetPassword(String token,String newPassword) {
		
		User user = userRepo.findByResetToken(token).orElseThrow(()-> new RuntimeException("token expire"));
	 
	    if (user.getTokenExpiry().isBefore(LocalDateTime.now()))
	    {
	    	throw new RuntimeException("token expired");
	    }
	    user.setPassword(passwordEncoder.encode(newPassword));
	user.setResetToken(null);
	user.setTokenExpiry(null);
	userRepo.save(user);
	}
}
