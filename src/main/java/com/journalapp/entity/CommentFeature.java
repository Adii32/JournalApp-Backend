package com.journalapp.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CommentFeature {
	@Id
	@GeneratedValue
	private Long Id;
	@ManyToOne
	@JsonIgnore
	private User user;
	@ManyToOne
	@JsonIgnore
	private JournalEntry journalEntry;
	private String text;
	private LocalDateTime commentAt;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public JournalEntry getJournalEntry() {
		return journalEntry;
	}
	public void setJournalEntry(JournalEntry journalEntry) {
		this.journalEntry = journalEntry;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getCommentAt() {
		return commentAt;
	}
	public void setCommentAt(LocalDateTime commentAt) {
		this.commentAt = commentAt;
	}
	public CommentFeature(Long id, User user, JournalEntry journalEntry, String text, LocalDateTime commentAt) {
		super();
		Id = id;
		this.user = user;
		this.journalEntry = journalEntry;
		this.text = text;
		this.commentAt = commentAt;
	}
	public CommentFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

}
