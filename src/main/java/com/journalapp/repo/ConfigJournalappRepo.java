package com.journalapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.journalapp.entity.ConfigJournalapp;

public interface ConfigJournalappRepo extends JpaRepository<ConfigJournalapp, String> {

}
