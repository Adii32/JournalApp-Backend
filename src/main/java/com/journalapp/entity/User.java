package com.journalapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;



@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId")
private Long userId;
	@Column(unique=true,nullable=false)
	private String userName;
	private String email;
	private boolean sentimentAnalysis;
	private String profilePic;
	private String cloudinaryPublicId;
	private LocalDateTime tokenExpiry;
	private String resetToken;
@OneToMany(mappedBy = "sender" , cascade=CascadeType.ALL,orphanRemoval = true)

@JsonIgnore
private List<FriendRequest> sentFriendRequest;
@OneToMany(mappedBy = "reciever" , cascade = CascadeType.ALL,orphanRemoval = true)

@JsonIgnore
private List<FriendRequest> recievedFriendRequest;
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "user_friend",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id")
)
@JsonIgnore
private Set<User> friend = new HashSet<>();
@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true)
private List<LikeFeature> likes = new ArrayList<>();
@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true)
private List<CommentFeature> comments = new ArrayList<>();

public List<LikeFeature> getLikes() {
	return likes;
}

public void setLikes(List<LikeFeature> likes) {
	this.likes = likes;
}

public List<CommentFeature> getComments() {
	return comments;
}

public void setComments(List<CommentFeature> comments) {
	this.comments = comments;
}

public List<FriendRequest> getSentFriendRequest() {
	return sentFriendRequest;
	
}

public void setSentFriendRequest(List<FriendRequest> sentFriendRequest) {
	this.sentFriendRequest = sentFriendRequest;
}

public List<FriendRequest> getRecievedFriendRequest() {
	return recievedFriendRequest;
}

public void setRecievedFriendRequest(List<FriendRequest> recievedFriendRequest) {
	this.recievedFriendRequest = recievedFriendRequest;
}

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
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    User user = (User) o;
	    return Objects.equals(userId, user.userId);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(userId);
	}


	public User(Long userId, String userName, String email, boolean sentimentAnalysis, String profilePic,
			String cloudinaryPublicId, LocalDateTime tokenExpiry, String resetToken,
			List<FriendRequest> sentFriendRequest, List<FriendRequest> recievedFriendRequest, Set<User> friend,
			List<LikeFeature> likes, List<CommentFeature> comments, String roles, String password,
			List<JournalEntry> entry) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.sentimentAnalysis = sentimentAnalysis;
		this.profilePic = profilePic;
		this.cloudinaryPublicId = cloudinaryPublicId;
		this.tokenExpiry = tokenExpiry;
		this.resetToken = resetToken;
		this.sentFriendRequest = sentFriendRequest;
		this.recievedFriendRequest = recievedFriendRequest;
		this.friend = friend;
		this.likes = likes;
		this.comments = comments;
		Roles = roles;
		this.password = password;
		this.entry = entry;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<User> getFriend() {
		return friend;
	}

	public void setFriend(Set<User> friend) {
		this.friend = friend;
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
