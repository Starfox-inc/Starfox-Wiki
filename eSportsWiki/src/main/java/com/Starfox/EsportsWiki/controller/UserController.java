package com.Starfox.EsportsWiki.controller;

import com.Starfox.EsportsWiki.service.AuthenticationService;
import com.Starfox.EsportsWiki.dto.RegistrationRequest; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Starfox.EsportsWiki.model.User;
import com.Starfox.EsportsWiki.repository.UserDAO;
import com.Starfox.EsportsWiki.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller  //Changed from @RestController to @Controller
@RequestMapping("/users")  // Simplified the path for easier form action mapping
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationService authenticationService;
    private final UserDAO userDAO;

    @Autowired
    public UserController(AuthenticationService authenticationService, UserDAO userDAO) {
        this.authenticationService = authenticationService;
        this.userDAO = userDAO;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        //display an empty form
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "createaccount";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute ("registrationRequest") RegistrationRequest registrationRequest, BindingResult result, @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        logger.info("Registering user: " + registrationRequest.getUsername());
        
        //Check for validation errors in the form fields
        if (result.hasErrors()) {
            //Logging the errors
        result.getAllErrors().forEach(error -> logger.error(error.toString()));
        
        redirectAttributes.addFlashAttribute("errorMessage", "Validation errors occurred");
            return "createaccount"; //Return to the registration form if there are errors
        }

        //Manually check if passwords match
        if (!registrationRequest.getPassword().equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords do not match.");
            return "redirect:/users/register"; //Return to the registration form
        }
        
        boolean registrationResult = authenticationService.registerUser(
            registrationRequest.getUsername(),
            registrationRequest.getEmail(),
            registrationRequest.getPassword());

        if (registrationResult) {
            redirectAttributes.addFlashAttribute("successMessage", "User registration successful");
            return "redirect:/users/login";  //Redirect to the login page
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed");
            return "redirect:/users/register";  //Stay on the registration page
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && authenticationService.verifyPassword(username, password)) {
            redirectAttributes.addFlashAttribute("successMessage", "Login successful");
            return "redirect:/dashboard";  //Redirect to dashboard /correct user page
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
            return "redirect:/users/login";  //Stay on the login page
        }
    }

    @GetMapping("/update/{userId}")
    public String showUpdateForm(@PathVariable int userId, Model model) {
        User user = userDAO.getUserByUsername(String.valueOf(userId));
        if (user != null) {
            model.addAttribute("user", user);
            return "settings";  //The form to update user details
        } else {
            return "redirect:/users/login";  //Redirect if user not found
        }
    }

    @PutMapping("/update/{userId}")
    public String updateUser(@PathVariable int userId, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        user.setUserId(userId);
        boolean isUpdated = userDAO.updateUser(user);

        if (isUpdated) {
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
            return "redirect:/dashboard";  //Redirect to the user dashboard or settings page
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            return "redirect:/users/update/" + userId;  //Retry update
        }
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId, RedirectAttributes redirectAttributes) {
        boolean isDeleted = userDAO.deleteUser(userId);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
            return "redirect:/users";  //Redirect to the user list /correct page
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            return "redirect:/dashboard";  //Redirect back to dashboard or user list
        }
    }


}