package TechTigers.BicycleBuddies.models.dto;

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;
    private String displayName;
    private String email;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String fullName) {
        this.displayName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
