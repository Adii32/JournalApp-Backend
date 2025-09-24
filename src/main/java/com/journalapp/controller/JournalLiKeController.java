package com.journalapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.DTO.LikeDTO;
import com.journalapp.entity.User;
import com.journalapp.service.LikeService;

@RequestMapping("/like")
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JournalLiKeController {
	@Autowired
	private LikeService likeService;
	@PostMapping("/{journalId}")
	public ResponseEntity<String> doLike(@PathVariable Long journalId,@RequestParam Long userId){
		Boolean liked = likeService.doLike(userId, journalId);
		if(liked) {
			return ResponseEntity.ok("liked successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already like this journal");
		
	} 
	@DeleteMapping("/dislike/{journalId}")
	public ResponseEntity<String> unLike(@PathVariable Long journalId,@RequestParam Long userId){
		Boolean disliked = likeService.unLike(userId, journalId);
		if(disliked) {
			return ResponseEntity.ok("disLike successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already dislike this journal");
		
	} 
	@GetMapping("/count/{journalId}")
	public ResponseEntity<Long> getAllLikeCounts(@PathVariable("journalId") Long journalId){
		Long count = likeService.totalLikeCount(journalId);
		return ResponseEntity.ok(count);
		
	}
	@GetMapping("/getAllUsers/{journalId}")
public ResponseEntity<List<User>> getUserWhoLiked(@PathVariable Long journalId){
		List<User> users = likeService.findUserWhoLiked(journalId);
		return ResponseEntity.ok(users);
		
	}
	@GetMapping("/getAllUserByLikedTime/{journalId}")
	public ResponseEntity<List<LikeDTO>> getAllUsersByLikedTime(@PathVariable Long journalId){
		List<LikeDTO> usersWithLikeTime = likeService.findUserByLikeTime(journalId);
		return ResponseEntity.ok(usersWithLikeTime);
		
	}
}
