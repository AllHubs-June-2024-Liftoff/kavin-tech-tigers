package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.LoginFormDTO;
import TechTigers.BicycleBuddies.models.dto.RegisterFormDTO;
import TechTigers.BicycleBuddies.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final String userSessionKey = "user";

    // Get User from Session
    public User getUserFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    // Set User in Session
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    // Display Registration Form
    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    // Process Registration Form
    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        // Check if user already exists
        User existingUser = userRepository.findByUserName(registerFormDTO.getUserName());
        if (existingUser != null) {
            errors.rejectValue("userName", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        // Password and Confirm Password matching
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        // Create and save new user
        User newUser = new User(registerFormDTO.getUserName(), registerFormDTO.getEmail(),
                registerFormDTO.getPassword(), registerFormDTO.getDisplayName());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/email"; // Redirect to email verification page
    }

    // Display Login Form
    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    // Process Login Form
    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        // Check if user exists
        User theUser = userRepository.findByUserName(loginFormDTO.getUserName());
        if (theUser == null) {
            errors.rejectValue("userName", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        // Password validation
        String password = loginFormDTO.getPassword();
        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        // Check if account is verified
        if (!theUser.isVerified()) {
            errors.rejectValue("password", "password.invalid", "Account not yet verified!");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);
        return "redirect:/login-verification";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
