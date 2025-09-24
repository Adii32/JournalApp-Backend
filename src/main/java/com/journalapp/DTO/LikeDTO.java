package com.journalapp.DTO;

import java.time.LocalDateTime;

public class LikeDTO {
private Long userId;
private LocalDateTime likedAt;
private String userName;
private String profilePic;
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public LocalDateTime getLikedAt() {
	return likedAt;
}
public void setLikedAt(LocalDateTime likedAt) {
	this.likedAt = likedAt;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}

public LikeDTO(Long userId, LocalDateTime likedAt, String userName, String profilePic) {
	super();
	this.userId = userId;
	this.likedAt = likedAt;
	this.userName = userName;
	this.profilePic = profilePic;
}
public String getProfilePic() {
	return profilePic;
}
public void setProfilePic(String profilePic) {
	this.profilePic = profilePic;
}
public LikeDTO() {
	super();
	// TODO Auto-generated constructor stub
}

}
