package com.journalapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalapp.service.EmailService;

@SpringBootTest
public class EmailServiceTest {
	
@Autowired
private EmailService emailService;
@Test
public void emailSendTest() {
	emailService.sendEmail("coderadii14@gmail.com", "is bat ka javab do", "kese ho");
}

}
