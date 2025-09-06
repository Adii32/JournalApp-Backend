package com.journalapp.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.journalapp.Info.ImgInfo;
import com.journalapp.entity.User;
import com.journalapp.exception.ResourceNotFound;
import com.journalapp.repo.UserRepo;



@Service

public class UserService {
	@Autowired
	private EmailService emailService;
	private UserRepo userRepo;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private ImageUploadService imageService;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	public void saveEntry(User user) {
		userRepo.save(user);
	}
public User createUser(User user,MultipartFile imageFile) throws IOException {
	ImgInfo imgInfo = imageService.upload(imageFile);
	user.setCloudinaryPublicId(imgInfo.public_id());
	user.setProfilePic(imgInfo.secure_url());

user.setPassword(passwordEncoder.encode(user.getPassword()));
user.setRoles("user");

	return userRepo.save(user);
}
public List<User> getAll() {
	return userRepo.findAll();
}
public  User  getById(Long id) {
	return userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("user not found"));
}
public void deleteUser(String name) {
	 User  user = userRepo.findByUserName(name);
	userRepo.delete(user);
}
public User findByUserName(String username) {
	return userRepo.findByUserName(username);
}
public User updateUser(String username,User user1) {
	User user = userRepo.findByUserName(username);
user.setUserName(user1.getUserName());
user.setPassword(passwordEncoder.encode(user1.getPassword()));
	
	userRepo.save(user);
return user;
}

	
}





