package com.journalapp.controller;

import java.util.List;
import java.util.Set;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.entity.FriendRequest;
import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.service.FriendRequestService;

@RequestMapping("/friend")
@RestController
public class FriendRequestController {
	@Autowired
	private FriendRequestService frndRequestService;
	@PostMapping("/send")
	public ResponseEntity<?> sendRequest(@RequestParam Long senderId,@RequestParam Long receiverId) {
		try {
			FriendRequest request = frndRequestService.sendRequest(senderId,receiverId);
			
			return ResponseEntity.ok(request);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
		
	}
	@PostMapping("/{requestId}/accept")
	public ResponseEntity<?> acceptRequest(@PathVariable Long requestId){
		
		try {
			FriendRequest request  = frndRequestService.acceptFriendRequest(requestId);
			return ResponseEntity.ok(request);
			
		} catch (Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	@PostMapping("/{requestId}/reject")
	public ResponseEntity<?> rejectRequest(@PathVariable Long requestId){
		
		try {
			FriendRequest request  = frndRequestService.rejectFriendRequest(requestId);
			return ResponseEntity.ok(request);
			
		} catch (Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

	@GetMapping("/pending/{userId}")
	public ResponseEntity<List<FriendRequest>> PendingRequest(@PathVariable Long userId){
		
	
			List<FriendRequest> request  = frndRequestService.getAllPedingRequests(userId);
			return ResponseEntity.ok(request);
			
		
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Set<User>> getAllFriends(@RequestParam Long id){
		Set<User> users = frndRequestService.getAllFriedns(id);
		return ResponseEntity.ok(users);
		
	}
	

}
