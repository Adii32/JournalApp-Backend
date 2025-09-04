package com.journalapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.journalapp.repo.UserRepoImpl;

@SpringBootTest
public class UserRepositoryImplTest {
	@Autowired
	private UserRepoImpl userRepoImpl;
	@Test
	public void testSaveNewUser()
	{

		 Object users = userRepoImpl.getUsersForSA();
		
		 
	        assertNotNull(users, "getUsersForSA() should not return null");
	}
}
