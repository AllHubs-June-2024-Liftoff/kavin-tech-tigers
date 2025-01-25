package TechTigers.BicycleBuddies.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {

    @NotBlank(message = "Username is required")
    @Size(min=3, max=20, message = "Invalid username. Must be between 3 and 20 characters.")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min=5, max=30, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}