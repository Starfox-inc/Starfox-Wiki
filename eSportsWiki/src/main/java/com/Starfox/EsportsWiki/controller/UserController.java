package com.Starfox.EsportsWiki.controller;

import com.Starfox.EsportsWiki.service.AuthenticationService;
import com.Starfox.EsportsWiki.dto.RegistrationRequest; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Starfox.EsportsWiki.model.User;
import com.Starfox.EsportsWiki.repository.UserDAO;
import com.Starfox.EsportsWiki.dto.LoginRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserDAO userDAO;

    @Autowired
    public UserController(AuthenticationService authenticationService, UserDAO userDAO) {
        this.authenticationService = authenticationService;
        this.userDAO = userDAO;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        boolean registrationResult = authenticationService.registerUser(
            registrationRequest.getUsername(),
            registrationRequest.getEmail(),
            registrationRequest.getPassword());

    if (registrationResult) {
        return new ResponseEntity<>("User registration successful", HttpStatus.CREATED);
    } else {
            return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) {
        user.setUserId(userId); // Make sure the user's ID is set to the path variable
        boolean isUpdated = userDAO.updateUser(user);

        if (isUpdated) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    //Endpoint to delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        boolean isDeleted = userDAO.deleteUser(userId);

        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userDAO.getUserByUsername(loginRequest.getUsername());
        if (user != null && authenticationService.verifyPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}