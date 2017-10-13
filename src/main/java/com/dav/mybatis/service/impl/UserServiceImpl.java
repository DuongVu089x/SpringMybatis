package com.dav.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dav.mybatis.dao.UserDao;
import com.dav.mybatis.model.User;
import com.dav.mybatis.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.dav.mybatis.service.UserService#getUserByUsername(java.lang.String)
     */
    @Override
    public User getUserByUsername(String username) throws Exception {
        return userDao.getUserByUsername(username);
    }

    @Override
    public void save(User user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userDao.save(user);
    }

}
