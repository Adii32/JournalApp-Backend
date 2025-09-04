package com.journalapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.journalapp.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,Long >{
	
	User findByUserName(String username);
void deleteByUserName(String userName);
}
