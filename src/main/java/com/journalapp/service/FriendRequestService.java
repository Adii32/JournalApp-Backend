package com.journalapp.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journalapp.entity.FriendRequest;
import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repo.FriendRequestRepo;
import com.journalapp.repo.FriendrRequestRepo;
import com.journalapp.repo.UserRepo;

import io.jsonwebtoken.lang.Collections;
import jakarta.transaction.Transactional;

@Service
public class FriendRequestService {
@Autowired 
private FriendRequestRepo friendRequestRepo;
@Autowired
private UserRepo userRepo;
public FriendRequest sendRequest(Long senderId,Long receiverId) {
	User sender = userRepo.findById(senderId).orElseThrow(()->new RuntimeException("user not found with this id"));
	User receiver = userRepo.findById(receiverId).orElseThrow(()-> new RuntimeException("user not found with this id"));
if(friendRequestRepo.findBySenderAndReceiver(sender,receiver).isPresent()) {
	throw new RuntimeException("both are already friends");
	
}
if ((sender.getFriend()).stream().anyMatch(u -> u.getUserId().equals(receiver.getUserId()))) {
    throw new RuntimeException("Users are already friends");
}

FriendRequest request = new FriendRequest();
request.setSender(sender);
request.setReceiver(receiver);
request.setStatus(FriendRequest.Status.PENDING);
return friendRequestRepo.save(request);
}
@Transactional
public FriendRequest acceptFriendRequest(Long requestId) {
	FriendRequest request = friendRequestRepo.findById(requestId).orElseThrow(()-> new RuntimeException("no request with this id"));
	if(request.getStatus() != FriendRequest.Status.PENDING) {
		throw new RuntimeException("user is already processed");
	}
	request.setStatus(FriendRequest.Status.ACCEPTED);
	User sender = request.getSender();
	User receiver = request.getReceiver();
	sender.getFriend().add(receiver);
	receiver.getFriend().add(sender);
	userRepo.save(sender);
	userRepo.save(receiver);
	return friendRequestRepo.save(request);
	
}
public FriendRequest rejectFriendRequest(Long requestId) {
	
	FriendRequest request = friendRequestRepo.findById(requestId).orElseThrow(()-> new RuntimeException("no request with this id"));
	if(request.getStatus() != FriendRequest.Status.PENDING) {
		throw new RuntimeException("user already processed");		
	}
	request.setStatus(FriendRequest.Status.REJECTED);
	return friendRequestRepo.save(request);
}
public List<FriendRequest> getAllPedingRequests(Long userId) {
	
	User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("user not found with this id"));
return friendRequestRepo.findByReceiverAndStatus(user, FriendRequest.Status.PENDING);

}
public Set<User> getAllFriedns(Long id){
	User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
	return user.getFriend();
	
}
public List<JournalEntry> getJournalIfFriends(Long ownerID,Long viewerID){
	User viewer = userRepo.findById(viewerID).orElseThrow(()-> new RuntimeException("user not found"));
	User owner = userRepo.findById(ownerID).orElseThrow(()-> new RuntimeException("user not found"));
	if(viewer.getFriend().contains(owner)) {
		
		return owner.getEntry();
	} 
	
	return Collections.emptyList();
}
}
	
	
	
	
	
	
	