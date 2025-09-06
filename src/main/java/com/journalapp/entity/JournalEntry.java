package com.journalapp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.journalapp.Enum.Category;
import com.journalapp.Enum.Sentiments;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class JournalEntry {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;
private String title;
private String content;
private LocalDateTime date;
private String img;
private String cloudinaryPublicId;
private boolean favorite;
@Enumerated(EnumType.STRING)
private Category category;
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getCloudinaryPublicId() {
	return cloudinaryPublicId;
}
public void setCloudinaryPublicId(String cloudinaryPublicId) {
	this.cloudinaryPublicId = cloudinaryPublicId;
}
@Enumerated(EnumType.STRING)
private Sentiments sentiments;

public boolean isFavorite() {
	return favorite;
}
public void setFavorite(boolean favorite) {
	this.favorite = favorite;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
@ManyToOne
@JsonIgnore
@JoinColumn(name="user_id")
private User user;
public Sentiments getSentiments() {
	return sentiments;
}
public void setSentiments(Sentiments sentiments) {
	this.sentiments = sentiments;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public LocalDateTime getDate() {
	return date;
}
public void setDate(LocalDateTime date) {
	this.date = date;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

public JournalEntry(Long id, String title, String content, LocalDateTime date, String img, String cloudinaryPublicId,
		boolean favorite, Category category, Sentiments sentiments, User user) {
	super();
	this.id = id;
	this.title = title;
	this.content = content;
	this.date = date;
	this.img = img;
	this.cloudinaryPublicId = cloudinaryPublicId;
	this.favorite = favorite;
	this.category = category;
	this.sentiments = sentiments;
	this.user = user;
}
public JournalEntry() {
	super();
	// TODO Auto-generated constructor stub
}


}