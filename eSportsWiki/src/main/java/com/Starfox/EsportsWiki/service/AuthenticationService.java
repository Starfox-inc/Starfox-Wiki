package com.Starfox.EsportsWiki.service;

import com.Starfox.EsportsWiki.model.User; 
import com.Starfox.EsportsWiki.repository.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthenticationService {

    private UserDAO userDao;

    @Autowired
    public AuthenticationService(UserDAO userDao) {
        this.userDao = userDao;
    }

    //Hashing a plain text password using BCrypt
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    //Register a new user
    public boolean registerUser(String username, String email, String plainTextPassword) {
        /*if (!plainTextPassword.equals(confirmPassword)) {
            return false; // Passwords do not match
        }*/
        log.debug("Attempting to register user: {}", username);
        //check if the username or email already exists
        if (userDao.getUserByUsername(username) != null) {
            //Handles the case where a user already exists with the username
            return false;
        }

        //Hash plain text password
        String hashedPassword = hashPassword(plainTextPassword);

        //Create a new User object
        User newUser = new User(username, email, hashedPassword);

        //Using the UserDao to save the new user to the database
        return userDao.addUser(newUser);
    }

    //Verify the user's password
    public boolean verifyPassword(String username, String plainTextPassword) {
        //get the stored user from the database
        User user = userDao.getUserByUsername(username);

        if (user != null) {
            //Check the password hash
            return BCrypt.checkpw(plainTextPassword, user.getPasswordHash());
        }

        return false;
    }
}