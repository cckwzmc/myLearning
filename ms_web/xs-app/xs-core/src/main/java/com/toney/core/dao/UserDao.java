package com.toney.core.dao;

import com.toney.core.model.User;

public interface UserDao {
    User getUser(Long id);

    User getUserByUsername(String username);

    void addUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);
}
