package TechTigers.BicycleBuddies.models.dto;

//used for registering account for first time
public class VerifyFormDTO {

    private int userSubmittedVerification;

    public int getUserSubmittedVerification() {
        return userSubmittedVerification;
    }

    public void setUserSubmittedVerification(int userSubmittedVerification) {
        this.userSubmittedVerification = userSubmittedVerification;
    }

}