package com.journalapp.controller;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repo.JournalRepo;
import com.journalapp.repo.JournalRepoImpl;
import com.journalapp.service.EmailService;
import com.journalapp.service.JournalService;
import com.journalapp.service.UserService;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import jakarta.mail.Multipart;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

	private JournalService journalService;

@Autowired
private JournalRepoImpl journalRepoImpl;
	
	public JournalEntryController(JournalService journalService) {
		this.journalService = journalService;
	}
	@Autowired
	private JournalRepo journalRepo;

@Autowired
private UserService userService;


@Autowired
private EmailService emailService;
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
public ResponseEntity<JournalEntry> createEntry(@ModelAttribute JournalEntry myentry,@RequestParam("imageFile")MultipartFile imageFile) throws IOException {
	try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
	
		 myentry.setDate(LocalDateTime.now());
		 myentry.setUser(user);
		 JournalEntry entry = journalService.createJournal(myentry, imageFile);
		 user.getEntry().add(entry);	
	
		 userService.saveEntry(user);
		 emailService.sendEmail(user.getEmail(),"new journal added","title of journal entry is "+myentry.getTitle());
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
	User user = userService.findByUserName(username);
	JournalEntry deleteEntry = journalRepo.findById(id).orElse(null);
	String title = deleteEntry != null ? deleteEntry.getTitle() : "unknown title";
	boolean removed = journalService.deleteEntry(id, username);
	if(removed) {
		emailService.sendEmail(
    user.getEmail(),
    "Journal entry deleted",
    "Title of journal entry is " +title
);

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
			emailService.sendEmail(
    user.getEmail(),
    "Journal entry updated",
    "the title of updated journal entry is "+newEntry.getTitle() 
);
	
	return new ResponseEntity<>(newEntry,HttpStatus.OK);
	}
}

		

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	

}
@GetMapping("/search")
public ResponseEntity<List<JournalEntry>> searchByKeyword(@RequestParam("keyword") String keyword) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String username = authentication.getName();
List<JournalEntry> list = journalRepo.searchByKeyword(username,keyword);
if(list != null) {
	System.out.println(list);
	return new ResponseEntity<>(list,HttpStatus.OK);
}
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@GetMapping("/favorites")
public ResponseEntity<List<JournalEntry>> getAllFavoriteJournal(){
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String username = authentication.getName();
	
List<JournalEntry> journals = journalRepoImpl.findFavorites(username);
if(journals != null) {
	return new ResponseEntity<>(journals,HttpStatus.OK);
}
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@GetMapping("/export/txt")
public ResponseEntity<byte[]> exportToTxtFile(){
	List<JournalEntry> list = journalRepo.findAll();
	byte[] content = journalService.generateTxtFile(list); 
	journalService.saveTxtFile(list, content);
	 return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=all-journal-entries.txt")
	            .contentType(MediaType.TEXT_PLAIN)
	            .body(content);

}
}
