package com.journalapp.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LikeFeature {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JsonIgnore
private User user;
	@ManyToOne
	@JsonIgnore
	private JournalEntry journalEntry;
	private LocalDateTime likedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public JournalEntry getJouranlEntry() {
		return journalEntry;
	}
	public void setJouranlEntry(JournalEntry jouranlEntry) {
		this.journalEntry = jouranlEntry;
	}
	public LocalDateTime getLikedAt() {
		return likedAt;
	}
	public void setLikedAt(LocalDateTime likedAt) {
		this.likedAt = likedAt;
	}
	public LikeFeature(Long id, User user, JournalEntry jouranlEntry, LocalDateTime likedAt) {
		super();
		this.id = id;
		this.user = user;
		this.journalEntry = jouranlEntry;
		this.likedAt = likedAt;
	}
	public LikeFeature() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
