package com.journalapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalapp.Schedular.UserSchedular;

@SpringBootTest
public class FetchUserAndSendSaMail {
@Autowired
private UserSchedular userSchedular;
@Test
public void testFetchUserAndSendSaMail(){
  userSchedular.fetchUsersAndSendSaMail();
}
}
