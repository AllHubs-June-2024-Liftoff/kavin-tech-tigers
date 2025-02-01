package TechTigers.BicycleBuddies.components;

import TechTigers.BicycleBuddies.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void scheduleEmailSending() {
        emailService.sendScheduledEmails();
    }
}
