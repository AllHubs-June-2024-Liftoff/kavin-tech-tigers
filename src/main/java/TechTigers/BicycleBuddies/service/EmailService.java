package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.RideUserRepository;
import TechTigers.BicycleBuddies.data.ScheduledEmailRepository;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.RideUser;
import TechTigers.BicycleBuddies.models.ScheduledEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private ScheduledEmailRepository emailRepository;

    @Autowired
    private RideUserRepository rideUserRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendScheduledEmails() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = LocalDateTime.now().plusMinutes(1L);
        List<ScheduledEmail> emailsToSend = emailRepository.findByEmailTimeBetween(now, later);
        System.out.println("Processing Emails: " + emailsToSend.size());
        emailsToSend.forEach(x -> processRide(x.getRide()));
    }

    private void processRide(final Ride ride) {
        List<RideUser> rideUsers = rideUserRepository.findByRide(ride);

        for (RideUser rideUser : rideUsers) {
            if (rideUser.getUser().getEmail() != null) {
                sendEmail(rideUser.getUser().getEmail());
            } else {
                System.err.println("no valid email address");
            }
        }
    }

    private void sendEmail(String emailAddress) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject("Time for Ride Test Subject");
        message.setText("Time for Ride Test Body");
        mailSender.send(message);
    }
}