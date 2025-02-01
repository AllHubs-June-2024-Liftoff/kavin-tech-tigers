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
            if (rideUser.getUser() != null && rideUser.getUser().getEmail() != null) {
                sendRideReminderEmail(rideUser);
            } else {
                System.err.println("no valid email address");
            }
        }
    }

    private void sendRideReminderEmail(RideUser rideUser) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(rideUser.getUser().getEmail());
        message.setSubject(rideUser.getUser().getUserName() + " You have an upcoming ride!");
        message.setText("Hello " + rideUser.getUser().getUserName() + "\nYour ride starts at" + rideUser.getRide().getDate() + "\nAnd the GPS co-ordinates for the meet up are"
                + rideUser.getRide().getLatitude() + " Latitude" + rideUser.getRide().getLongitude() + " Longitude\n\nGo Have fun!");
        mailSender.send(message);
    }
}
