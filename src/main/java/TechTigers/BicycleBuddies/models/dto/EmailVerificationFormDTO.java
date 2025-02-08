package TechTigers.BicycleBuddies.models.dto;

import javax.validation.constraints.NotNull;

public class EmailVerificationFormDTO {

    @NotNull(message = "Verification code is required")
    private Integer userSubmittedEmailVerification;

    public Integer getUserSubmittedEmailVerification() {
        return userSubmittedEmailVerification;
    }

    public void setUserSubmittedEmailVerification(Integer userSubmittedEmailVerification) {
        this.userSubmittedEmailVerification = userSubmittedEmailVerification;
    }
}
