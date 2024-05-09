package com.Starfox.EsportsWiki.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Starfox.EsportsWiki.dto.RegistrationRequest;
import com.Starfox.EsportsWiki.model.User;
import com.Starfox.EsportsWiki.repository.UserDAO;
import com.Starfox.EsportsWiki.repository.UserPreferencesDAO;
import com.Starfox.EsportsWiki.service.AuthenticationService;
import com.Starfox.EsportsWiki.service.CurrentTeamService;
import com.Starfox.EsportsWiki.service.CurrentVideoGameService;
import com.Starfox.EsportsWiki.service.MatchService;
import com.Starfox.EsportsWiki.service.PlayerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller  //Changed from @RestController to @Controller
@RequestMapping("/users")  //Simplified the path for easier form action mapping
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationService authenticationService;
    private final UserDAO userDAO;
    private final UserPreferencesDAO userPreferencesDAO;
    private final CurrentTeamService currentTeamService;
    private final CurrentVideoGameService currentVideoGameService;
    private final MatchService matchService;
    private final PlayerService playerService;

@Autowired
public UserController(AuthenticationService authenticationService, UserDAO userDAO, UserPreferencesDAO userPreferencesDAO,CurrentTeamService currentTeamService, CurrentVideoGameService currentVideoGameService,MatchService matchService, PlayerService playerService) {
    this.authenticationService = authenticationService;
    this.userDAO = userDAO;
    this.userPreferencesDAO = userPreferencesDAO;
    this.currentTeamService = currentTeamService;
    this.currentVideoGameService = currentVideoGameService;
    this.matchService = matchService;
    this.playerService = playerService;
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
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && authenticationService.verifyPassword(username, password)) {
            request.getSession().setAttribute("username", username);
            redirectAttributes.addFlashAttribute("successMessage", "Login successful");
            return "redirect:/users/settings";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
            return "redirect:/users/login";
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

    @PostMapping("/settings/delete")
    public String deleteAccount(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/users/login";
        }
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return "redirect:/users/login";
        }
        boolean isDeleted = userDAO.deleteUser(user.getUserId());
        if (isDeleted) {
            request.getSession().invalidate();
            redirectAttributes.addFlashAttribute("successMessage", "Account deleted successfully");
            return "redirect:/users/login";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete account");
            return "redirect:/users/settings";
        }
    }

    @GetMapping("/settings")
    public String showSettingsForm(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/users/login";
        }
        System.out.println(username);
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("user", user);
        boolean[] preferences = userPreferencesDAO.getUserPreferences(user.getUserId());
        model.addAttribute("liveDataPreference", preferences[0]);
        model.addAttribute("teamDataPreference", preferences[1]);
        model.addAttribute("playerDataPreference", preferences[2]);
        return "settings";
    }
    
    @PostMapping("/settings/email")
    public String updateEmail(@RequestParam(required = false) String email, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/users/login";
        }
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return "redirect:/users/login";
        }
        if (email != null && !email.isEmpty()) {
            if (userDAO.getUserByEmail(email) != null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Email already exists");
                return "redirect:/users/settings";
            }
            user.setEmail(email);
            if (userDAO.updateUser(user)) {
                redirectAttributes.addFlashAttribute("successMessage", "Email updated successfully");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update email");
            }
        }
        return "redirect:/users/settings";
    }
    
    @PostMapping("/settings/password")
    public String updatePassword(@RequestParam(required = false) String currentPassword, @RequestParam(required = false) String newPassword, @RequestParam(required = false) String confirmPassword, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/users/login";
        }
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return "redirect:/users/login";
        }
        if (currentPassword != null && newPassword != null && confirmPassword != null && !currentPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("errorMessage", "New password and confirm password do not match");
                return "redirect:/users/settings";
            }
            if (authenticationService.verifyPassword(username, currentPassword)) {
                user.setPasswordHash(authenticationService.hashPassword(newPassword));
                if (userDAO.updateUser(user)) {
                    redirectAttributes.addFlashAttribute("successMessage", "Password changed successfully");
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "Failed to change password");
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Current password is incorrect");
            }
        }
        return "redirect:/users/settings";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/users/login";
    }

    @PostMapping("/settings/preferences")
    public String saveUserPreferences(@RequestParam(required = false) boolean liveData, @RequestParam(required = false) boolean teamData, @RequestParam(required = false) boolean playerData,HttpServletRequest request, RedirectAttributes redirectAttributes) {
    String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/users/login";
        }
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return "redirect:/users/login";
        }
        userPreferencesDAO.saveUserPreferences(user.getUserId(), liveData, teamData, playerData);
        redirectAttributes.addFlashAttribute("successMessage", "Preferences saved successfully");
        return "redirect:/users/settings";
    }

    @GetMapping("/inbox")
    public String showInbox(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/users/login";
        }
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return "redirect:/users/login";
        }
        boolean[] preferences = userPreferencesDAO.getUserPreferences(user.getUserId());
        model.addAttribute("liveDataPreference", preferences[0]);
        model.addAttribute("teamDataPreference", preferences[1]);
        model.addAttribute("playerDataPreference", preferences[2]);
        if (preferences[0]) {
            //gets live data using the matchService
            model.addAttribute("liveData", matchService.findAllRunningMatches());
        }
        if (preferences[1]) {
            //gets team data using the currentTeamService
            model.addAttribute("teamData", currentTeamService.findAllTeams());
        }
        if (preferences[2]) {
            //gets player data using the playerService
            model.addAttribute("playerData", playerService.findAll());
        }
        return "inbox";
    }

}

