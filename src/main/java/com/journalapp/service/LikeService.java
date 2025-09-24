package com.journalapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journalapp.DTO.LikeDTO;
import com.journalapp.entity.LikeFeature;
import com.journalapp.entity.User;
import com.journalapp.repo.JournalRepo;
import com.journalapp.repo.LikeRepo;
import com.journalapp.repo.UserRepo;

@Service
public class LikeService {
	@Autowired
	private LikeRepo likeRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JournalRepo journalRepo;
	public boolean doLike(Long userId,Long journalId) {
		Optional<LikeFeature> exist =  likeRepo.findByUserIdAndJournalId(userId, journalId);
		
		if(exist.isPresent()) {
			return false;
		}
		LikeFeature like = new LikeFeature();
		like.setJouranlEntry(journalRepo.getById(journalId));
		like.setUser(userRepo.getById(userId));
		like.setLikedAt(LocalDateTime.now());
		likeRepo.save(like);
		return true;
	}
	public boolean unLike(Long userId,Long journalId) {
		Optional<LikeFeature> exist =  likeRepo.findByUserIdAndJournalId(userId, journalId);
		
		if(exist.isPresent()) {
			likeRepo.delete(exist.get());
			return true;
		}
		
		return false;
	}
	public Long totalLikeCount(Long journalId) {
		return likeRepo.findLikeCount(journalId);
		
	}
	public List<User> findUserWhoLiked(Long journalId){
	return likeRepo.findUserWhoLiked(journalId);
	}
	
	public List<LikeDTO> findUserByLikeTime(Long journalId){
		return likeRepo.findUserByLikeTime(journalId);
	}

}
