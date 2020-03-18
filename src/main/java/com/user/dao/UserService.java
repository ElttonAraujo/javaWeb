package com.user.dao;

import java.util.List;

import com.user.model.User;

public class UserService {

	private static UserDao userDao;
	 
    public UserService() {
		// TODO Auto-generated constructor stub
    	userDao = new UserDao();
	}
        
 
    public void add(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.add(entity);
        userDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }
 
    public User findById(Integer id) {
        userDao.openCurrentSession();
        User user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }
    
    public boolean isUser(User entity) {
        userDao.openCurrentSessionwithTransaction();
        boolean result = userDao.isUser(entity);
        userDao.closeCurrentSessionwithTransaction();
        return result;
    }
 
    public void delete(Integer id) {
        userDao.openCurrentSessionwithTransaction();
        User user = userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }
 
    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> books = userDao.findAll();
        userDao.closeCurrentSession();
        return books;
    }
 
 
    public UserDao userDao() {
        return userDao;
    }
}
