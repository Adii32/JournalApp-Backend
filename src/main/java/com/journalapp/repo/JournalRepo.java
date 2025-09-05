package com.journalapp.repo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.journalapp.entity.JournalEntry;
@Repository
public interface JournalRepo extends  JpaRepository<JournalEntry,Long> {
public void deleteById(Long id);
@Query("SELECT j FROM JournalEntry j " +
	       "WHERE j.user.userName = :username " +
	       "AND (LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	       "OR LOWER(j.content) LIKE LOWER(CONCAT('%', :keyword, '%'))"
	       +"OR LOWER(j.category) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<JournalEntry> searchByKeyword(@Param("username") String username,
	                                   @Param("keyword") String keyword);


}
