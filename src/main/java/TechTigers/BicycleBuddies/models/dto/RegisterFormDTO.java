package TechTigers.BicycleBuddies.models.dto;

import TechTigers.BicycleBuddies.models.dto.LoginFormDTO;

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;
    private String fullName;
    private String email;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
