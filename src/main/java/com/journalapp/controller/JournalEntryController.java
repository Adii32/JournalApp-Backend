package com.journalapp.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repo.JournalRepo;
import com.journalapp.service.JournalService;
import com.journalapp.service.UserService;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	private JournalService journalService;
	
	public JournalEntryController(JournalService journalService) {
		this.journalService = journalService;
	}
	
	private JournalRepo journalRepo;
@Autowired
private UserService userService;
private Map<Long,JournalEntry> entry = new HashMap<>();
@GetMapping
public ResponseEntity<?> getAllJournalEntryOfUser(){
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String username = authentication.getName();
User user = userService.findByUserName(username);
List<JournalEntry> all =  user.getEntry();
if(all != null && !all.isEmpty()) {
	return new ResponseEntity<>(all,HttpStatus.OK);
}
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@PostMapping
//@Transactional
public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry) {
	try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
	
		 myentry.setDate(LocalDateTime.now());
		 myentry.setUser(user);
		 JournalEntry entry = journalService.createJournal(myentry);
		 user.getEntry().add(entry);	
	
		 userService.saveEntry(user);
		 return new ResponseEntity<>(myentry,HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}

}
@GetMapping("id/{id}")
public ResponseEntity<JournalEntry> getById(@PathVariable Long id) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String username = authentication.getName();
	User user = userService.findByUserName(username);
	List<JournalEntry> collect = user.getEntry().stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList());
	if(!collect.isEmpty()) {
	Optional<JournalEntry> journalEntry = journalService.getById(id);
	if(journalEntry.isPresent()) {
		return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
	}
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
}
@DeleteMapping("id/{id}")
public ResponseEntity<?> deleteEntry(@PathVariable Long id) {

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String username = authentication.getName();
	boolean removed = journalService.deleteEntry(id, username);
	if(removed) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@PutMapping("id/{id}")
public ResponseEntity<?> updateEntry(@PathVariable Long id,@RequestBody JournalEntry newEntry) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String username = authentication.getName();
User user = userService.findByUserName(username);
List<JournalEntry> collect = user.getEntry().stream().filter(i-> i.getId().equals(id)).collect(Collectors.toList());
if(!collect.isEmpty()) {
	Optional<JournalEntry> oldEntry = journalService.getById(id);
	if(oldEntry.isPresent()){
		JournalEntry old = oldEntry.get();
		old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
		
		old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
	old.setDate(LocalDateTime.now());
		journalService.saveNewEntry(old);
	
	return new ResponseEntity<>(newEntry,HttpStatus.OK);
	}
}
		
		

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	

}
}
