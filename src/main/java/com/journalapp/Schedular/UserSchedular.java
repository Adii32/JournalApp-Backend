package com.journalapp.Schedular;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.journalapp.Enum.Sentiments;
import com.journalapp.cache.AppCache;
import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repo.UserRepoImpl;
import com.journalapp.service.EmailService;
import com.journalapp.service.SentimentAnalysis;

import jakarta.transaction.Transactional;
@Component
public class UserSchedular {
@Autowired
private UserRepoImpl userRepoImpl;
@Autowired
private SentimentAnalysis sentimentalAnalysis;
@Autowired
private EmailService emailService;
@Autowired
private AppCache appCache;
@Scheduled(cron = "0 21 14 * * WED")
@Transactional
public void fetchUsersAndSendSaMail() {
    
    List<User> users = userRepoImpl.getUsersForSA();
    for (User user : users) {
        List<JournalEntry> journalEntries = user.getEntry();
        List<Sentiments> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiments()).collect(Collectors.toList());
        Map<Sentiments, Integer> sentimentCounts = new HashMap<>();
        for (Sentiments sentiment : sentiments) {
            if (sentiment != null)
                sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
        }
        Sentiments mostFrequentSentiment = null;
        int maxCount = 0;
        for (Map.Entry<Sentiments, Integer> entry : sentimentCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentSentiment = entry.getKey();
            }
        }
        if (mostFrequentSentiment != null) {
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days ", mostFrequentSentiment.toString());
        }
    }
}

@Scheduled(cron = "0 30 10 * * *")
public void everydaynotification() {
List<User> users = userRepoImpl.getUsersForSA();
for(User user : users){
    emailService.sendEmail(user.getEmail(),"Daily Notification from journal app","Hello "+user.getUserName()+"write your journal today");
}
}

@Scheduled(cron = "0 0/10 * ? * *")
public void clearAppCache() {
    appCache.init();
}

}
