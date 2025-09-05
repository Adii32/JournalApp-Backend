package com.journalapp.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JournalRepoImpl {
@Autowired
private EntityManager entityManager;

    public List<JournalEntry> findFavorites(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JournalEntry> cq = cb.createQuery(JournalEntry.class);
        Root<JournalEntry> root = cq.from(JournalEntry.class);


        Join<JournalEntry, User> userJoin = root.join("user");

     
        Predicate usernamePredicate = cb.equal(userJoin.get("userName"), username);

   
        Predicate favoritePredicate = cb.isTrue(root.get("favorite"));

    

    
            cq.where(cb.and(usernamePredicate, favoritePredicate));
     

        return entityManager.createQuery(cq).getResultList();
    }
}
