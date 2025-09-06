package com.journalapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.Local;

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
	private String profilePic;
	private String cloudinaryPublicId;
	private LocalDateTime tokenExpiry;
	private String resetToken;

public LocalDateTime getTokenExpiry() {
		return tokenExpiry;
	}

	public void setTokenExpiry(LocalDateTime tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

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
			List<JournalEntry> entry,String profilePic,String cloudinaryPublicId,LocalDateTime tokenExpiry,String resetToken) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.email = email;
		this.sentimentAnalysis = sentimentAnalysis;
		Roles = roles;
		this.password = password;
		this.entry = entry;
		this.profilePic = profilePic;
		this.cloudinaryPublicId = cloudinaryPublicId;
		this.resetToken = resetToken;
		this.tokenExpiry = tokenExpiry;
	}

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCloudinaryPublicId() {
        return cloudinaryPublicId;
    }

    public void setCloudinaryPublicId(String cloudinaryPublicId) {
        this.cloudinaryPublicId = cloudinaryPublicId;
    }



}
