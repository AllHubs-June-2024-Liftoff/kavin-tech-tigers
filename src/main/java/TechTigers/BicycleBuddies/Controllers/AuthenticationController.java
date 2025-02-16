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

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUserName(registerFormDTO.getUserName());

        if (existingUser != null) {
            errors.rejectValue("userName", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        if (!registerFormDTO.getPassword().equals(registerFormDTO.getVerifyPassword())) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUserName(), registerFormDTO.getPassword(),
                registerFormDTO.getEmail(), registerFormDTO.getDisplayName());
        userRepository.save(newUser);
        userService.setUserInSession(request.getSession(), newUser); // Use UserService for session management

        return "redirect:/email";
    }

    @GetMapping("/login")
    public String displayLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");

        if ("true".equals(error)) {
            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        } else if ("not_verified".equals(error)) {
            model.addAttribute("errorMessage", "Account not yet verified! Please check your email.");
        }

        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUserName(loginFormDTO.getUserName());

        if (theUser == null || !theUser.isMatchingPassword(loginFormDTO.getPassword())) {
            return "redirect:/login?error=true"; // Redirect instead of returning a missing template
        }

        if (!theUser.isVerified()) {
            return "redirect:/login?error=not_verified";
        }

        userService.setUserInSession(request.getSession(), theUser);
        return "redirect:/login-verification";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
