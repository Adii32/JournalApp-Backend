package com.journalapp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalapp.entity.ConfigJournalapp;
import com.journalapp.repo.ConfigJournalappRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {
public Map<String,String> APP_CACHE = new HashMap<>();
	@Autowired
	private ConfigJournalappRepo configjournalappRepo;
	@PostConstruct
	public void init() {
		List<ConfigJournalapp> list = configjournalappRepo.findAll();
		for(ConfigJournalapp configJournal : list) {
			APP_CACHE.put(configJournal.getKeys(), configJournal.getValues());
		}
		System.out.println("appCache"+APP_CACHE);
	}


}
