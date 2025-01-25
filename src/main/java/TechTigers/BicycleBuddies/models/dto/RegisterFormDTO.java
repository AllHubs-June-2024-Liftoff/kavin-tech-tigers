package TechTigers.BicycleBuddies.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterFormDTO extends LoginFormDTO {

    @NotBlank(message = "Field is required")
    private String verifyPassword;

//    private String displayName;
//
    @NotBlank(message = "Field is required")
    @Email(message = "Please enter a valid email")
    private String email;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

//    public String getDisplayName() {
//        return displayName;
//    }
//
//    public void setDisplayName(String fullName) {
//        this.displayName = fullName;
//    }
//
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
