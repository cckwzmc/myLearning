package com.toney.core.biz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.toney.core.biz.UserDemoManager;
import com.toney.core.model.User;

/**
 *             
 *************************************************************** 
 * <p> 
 * @CLASS :com.xiu.portal.biz.impl.UserManagerImpl
 * @DESCRIPTION :TODO    
 * @AUTHOR :dick.xiao@xiu.com 
 * @VERSION :v1.0 
 * @DATE :2012-4-9 下午8:29:25             
 * </p>   
 ****************************************************************
 */
@Service("userDemoManager")
public class UserDemoManagerImpl implements UserDemoManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDemoManagerImpl.class);
//    
//    @Autowired
//    private UserDao userDao;
//
//    /**
//     * {@inheritDoc}
//     */
//    public User getUser(String userId) {
//        return userDao.getUser(Long.valueOf(userId));
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public User saveUser(User user) throws  Exception {
//
//        if (user.getId() == null) {
//            // if new user, lowercase userId
//            user.setUsername(user.getUsername().toLowerCase());
//        }
//
//        try {
//            if (user.getId() == null) {
//                userDao.addUser(user);
//            } else {
//                userDao.updateUser(user);
//            }
//            return user;
//
//        } catch (DataIntegrityViolationException e) {
//            LOGGER.warn(e.getMessage());
//            throw new Exception("User '" + user.getUsername() + "' already exists!"); 
//        } catch (JpaSystemException e) { // needed for JPA
//            LOGGER.warn(e.getMessage());
//            throw new Exception("User '" + user.getUsername() + "' already exists!"); 
//        }
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void removeUser(String userId) {
//        LOGGER.debug("removing user: " + userId);
//        userDao.deleteUser(Long.valueOf(userId));
//    }
//
//    /**
//     * {@inheritDoc}
//     * 
//     * @param username
//     *            the login name of the human
//     * @return User the populated user object
//     */
//    @Override
//    public User getUserByUsername(String username) throws Exception {
//        User user = userDao.getUserByUsername(username);
//        if (user == null) {
//            throw new Exception("user '" + username + "' not found...");
//        }
//        return user;
//    }

	@Override
	public User getUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(String userId) {
		// TODO Auto-generated method stub
		
	}


}
