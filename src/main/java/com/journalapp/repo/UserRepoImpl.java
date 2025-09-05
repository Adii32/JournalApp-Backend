package com.journalapp.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Repository
public class UserRepoImpl {
	@Autowired
	private EntityManager entityManager;
	public List<User> getUsersForSA(){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<User> cq = cb.createQuery(User.class);
	Root<User> user = cq.from(User.class);
   Predicate emailExist = cb.isNotNull(user.get("email"));
   Predicate patternEmail = cb.like(user.get("email"),"%@gmail.com");
   Predicate sentimentAnlysis = cb.isTrue(user.get("sentimentAnalysis"));
	cq.select(user).where(cb.and(emailExist,sentimentAnlysis,patternEmail));
	 List<User> user1 = entityManager.createQuery(cq).getResultList();
		return user1;
		
	}
	
	
	

	}



