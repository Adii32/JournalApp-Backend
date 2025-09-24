package com.journalapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.journalapp.entity.FriendRequest;
import com.journalapp.entity.User;
@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest,Long>{
Optional<FriendRequest> findBySenderAndReciever(User sender,User reciever);
List<FriendRequest> findByRecieverAndStatus(User reciever,FriendRequest.Status status);
}
