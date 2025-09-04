package com.journalapp.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long user_id;
	@Column(unique=true,nullable=false)
	private String userName;
	private String email;
	private boolean sentimentAnalysis;
	

public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSentimentAnalysis() {
		return sentimentAnalysis;
	}

	public void setSentimentAnalysis(boolean sentimentAnalysis) {
		this.sentimentAnalysis = sentimentAnalysis;
	}

private String Roles;
public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRoles() {
	return Roles;
}
public void setRoles(String roles) {
	Roles = roles;
}

	@Column(nullable=false)
private String password;
	
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
@JsonIgnore
List<JournalEntry> entry = new ArrayList<>();

	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<JournalEntry> getEntry() {
		return entry;
	}
	public void setEntry(List<JournalEntry> entry) {
		this.entry = entry;
	}

	public User(Long user_id, String userName, String email, boolean sentimentAnalysis, String roles, String password,
			List<JournalEntry> entry) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.email = email;
		this.sentimentAnalysis = sentimentAnalysis;
		Roles = roles;
		this.password = password;
		this.entry = entry;
	}



}
