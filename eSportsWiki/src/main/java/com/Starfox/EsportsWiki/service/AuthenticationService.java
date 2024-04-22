package com.Starfox.EsportsWiki.service;

import com.Starfox.EsportsWiki.model.User; 
import com.Starfox.EsportsWiki.repository.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    //Register a new user
    public boolean registerUser(String username, String email, String plainTextPassword) {
        //check if the username or email already exists
        if (userDao.getUserByUsername(username) != null) {
            //Handle the case where a user already exists with the given username
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