package com.journalapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.journalapp.DTO.LikeDTO;
import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.LikeFeature;
import com.journalapp.entity.User;

@Repository
public interface LikeRepo extends JpaRepository<LikeFeature, Long> {
	@Query("SELECT l FROM LikeFeature l WHERE l.user.userId = :userId AND l.journalEntry.id = :journalId")

	    Optional<LikeFeature> findByUserIdAndJournalId(@Param("userId") Long userId, @Param("journalId") Long journalId);
@Query("select count(l) from LikeFeature l where l.journalEntry.id = :journalId")
Long findLikeCount(@Param("journalId") Long journalId);
@Query("select l.user from LikeFeature l where l.journalEntry.id = :journalId")
List<User> findUserWhoLiked(@Param("journalId") Long journalId);
@Query("select new com.journalapp.DTO.LikeDTO(l.user.userId,l.likedAt,l.user.userName,l.user.profilePic) from LikeFeature l where l.journalEntry.id= :journalId")
List<LikeDTO> findUserByLikeTime(@Param("journalId") Long journalId);
}
