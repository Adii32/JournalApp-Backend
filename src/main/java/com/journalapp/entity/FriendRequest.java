package com.journalapp.entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FriendRequest {
	public enum Status{
		ACCEPTED,
		REJECTED,
		PENDING
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sender_id",nullable = false)
	private User sender;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reciever_id" , nullable = false)
private User reciever;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Status status = Status.PENDING;
	public FriendRequest(long id,  User sender, User reciever, Status status) {
		super();
		this.id = id;
		this.sender = sender;
		this.reciever = reciever;
		this.status = status;
	}
	public FriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciever() {
		return reciever;
	}
	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
}
