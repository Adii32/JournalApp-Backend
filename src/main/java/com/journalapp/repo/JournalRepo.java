package com.journalapp.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.journalapp.entity.JournalEntry;
@Repository
public interface JournalRepo extends  JpaRepository<JournalEntry,Long> {
public void deleteById(Long id);
}
