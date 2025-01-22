package TechTigers.BicycleBuddies.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Value("${TWILIO_ACCOUNT-SID}")
    String ACCOUNT_SID;

    //  TODO: ADD AND HIDE TWILIO AUTH TOKEN
    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;

    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER;

    public SMSService(String ACCOUNT_SID, String AUTH_TOKEN, String OUTGOING_SMS_NUMBER) {
        this.ACCOUNT_SID = ACCOUNT_SID;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.OUTGOING_SMS_NUMBER = OUTGOING_SMS_NUMBER;
    }

    @PostConstruct
    private void setup(){
        Twilio = init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public String sendSMS(String smsNumber, String smsMessage){
        return "nothing done";
    }

}
