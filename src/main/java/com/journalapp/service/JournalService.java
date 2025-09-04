package com.journalapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.exception.ResourceNotFound;
import com.journalapp.repo.JournalRepo;

import jakarta.transaction.Transactional;


@Service

public class JournalService {
	private JournalRepo journalRepo;
	@Autowired
	private UserService userService;


	public JournalService(JournalRepo journalRepo) {
		this.journalRepo = journalRepo;
	}
public JournalEntry createJournal(JournalEntry entry) {
	return journalRepo.save(entry);
}

public List<JournalEntry> getAll() {
	return journalRepo.findAll();
}

public JournalEntry findById(Long id) {
	return journalRepo.findById(id).orElseThrow(()-> new ResourceNotFound("journal not found"));
}
public Optional<JournalEntry> getById(Long id) {
	return journalRepo.findById(id);
}

@Transactional
public boolean deleteEntry(Long id,String username) {
	boolean entry = false;
	try {
	User  user = userService.findByUserName(username);
 entry = user.getEntry().removeIf(i -> i.getId().equals(id));
if(entry) {
	userService.saveEntry(user);
	journalRepo.deleteById(id);
}

} catch (Exception e) {
	throw new RuntimeException("an error occuring while deleting error ",e);
}
return entry;

}


public JournalEntry updateEntry(Long id,JournalEntry journalEntry) {
	JournalEntry journal = journalRepo.findById(id).orElseThrow(()-> new ResourceNotFound("journal not found"));
	journal.setTitle(journalEntry.getTitle());
journal.setContent(journalEntry.getContent());
journalRepo.save(journal);
return journal;
}

public JournalEntry saveNewEntry(JournalEntry journalEntry) {
	return journalRepo.save(journalEntry);
}
}
