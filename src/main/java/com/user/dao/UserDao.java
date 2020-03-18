package com.user.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.service.ServiceRegistry;
import org.omg.CORBA.UShortSeqHolder;

import com.user.model.User;

public class UserDao implements UserDaoInterface<User, Integer> {

	
	private Session currentSession;
    
    private Transaction currentTransaction;
 
    public UserDao() {
    }
 
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    
    @Override
    public void add(User entity) {
        getCurrentSession().save(entity);
    }
    
    @Override
    public void update(User entity) {
        getCurrentSession().update(entity);
    }
    
    @Override
    public User findById(Integer id) {
    	User user = (User) getCurrentSession().get(User.class, id);
        return user; 
    }
 
    @Override
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
        return users;
    }
    
    
    @Override
    public boolean isUser(User entity) {
    	// TODO Auto-generated method stub
    	boolean result = false;
    	User user = (User) getCurrentSession()
        		.createQuery("select u from User u where email = :email and password = :password")
        		.setParameter("email", entity.getEmail())
        		.setParameter("password", entity.getPassword()).uniqueResult();
        if(user != null) {
        	result = true;
        }
    	return result;
    }
    
}
