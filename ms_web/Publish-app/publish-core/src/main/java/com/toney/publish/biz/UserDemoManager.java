package com.toney.publish.biz;

import org.springframework.security.core.userdetails.User;


public interface UserDemoManager {

    /**
     * Finds a user by their username.
     * 
     * @param username
     *            the user's username used to login
     * @return User a populated user object
     */
    User getUserByUsername(String username) throws Exception;

    /**
     * Save a user's information.
     * 
     * @param user
     *            the user's information
     * @throws Exception
     *             thrown when user already exists
     * @return user the updated user object
     */
    User saveUser(User user) throws Exception;

    /**
     * Removes a user from the database by their userId
     * 
     * @param userId
     *            the user's id
     */
    void removeUser(String userId);

}
