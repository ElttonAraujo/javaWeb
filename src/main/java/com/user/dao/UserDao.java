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
 
    
//	static Session sessionObj;
//    static SessionFactory sessionFactoryObj;
// 
//    public final static Logger logger = Logger.getLogger(UserDao.class);
//    
////    public UserDao(SessionFactory factory) {
////    	this.sessionFactoryObj = factory;
////    }
// 
//    // This Method Is Used To Create The Hibernate's SessionFactory Object
//    private static SessionFactory buildSessionFactory() {
//        // Creating Configuration Instance & Passing Hibernate Configuration File
//        Configuration configObj = new Configuration();
//        configObj.configure("hibernate.cfg.xml");
// 
//        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
//        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
//        		.applySettings(configObj.getProperties()).build(); 
// 
//        // Creating Hibernate SessionFactory Instance
//        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
//        return sessionFactoryObj;
//    }
//    
// // Method 1: This Method Used To Create A New Student Record In The Database Table
//    public static void createRecord(User user) {
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            // Creating Transaction Entities 
//             sessionObj.save(user);
//            
//            // Committing The Transactions To The Database
//            sessionObj.getTransaction().commit();
//            logger.info("\nSuccessfully Created '" + user.getName() + "' Records In The Database!\n");
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if(sessionObj != null) {
//                sessionObj.close();
//            }
//        }
//    }
//    
// // Method 2: This Method Is Used To Display The Records From The Database Table
//    @SuppressWarnings("unchecked")
//    public static List<User> displayRecords() {
//        List<User> users = new ArrayList();        
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            users = sessionObj.createQuery("FROM User").list();
//            logger.info("first "+users.get(0).getName());
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if(sessionObj != null) {
//                sessionObj.close();
//            }
//        }
//        return users;
//    }
//    
// // Method 3: This Method Is Used To Update A Record In The Database Table   
//    public static void updateRecord(User user) {       
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            // Creating Transaction Entity
//            User userObject = (User) sessionObj.get(User.class, user.getId());
//            userObject.setName(user.getName());
//            userObject.setEmail(user.getEmail());
//            userObject.setPassword(user.getPassword());
// 
//            // Committing The Transactions To The Database
//            sessionObj.getTransaction().commit();
//            logger.info("\nUser With Id?= " + user.getId() + " Is Successfully Updated In The Database!\n");
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if(sessionObj != null) {
//                sessionObj.close();
//            }
//        }
//    }
//    
// // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
//    public static void deleteRecord(int id) {
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            User user = findRecordById(id);
//            sessionObj.delete(user);
// 
//            // Committing The Transactions To The Database
//            sessionObj.getTransaction().commit();
//            logger.info("\nStudent With Id?= " + id + " Is Successfully Deleted From The Database!\n");
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if(sessionObj != null) {
//                sessionObj.close();
//            }
//        }
//    }
//    
// // Method 4(b): This Method To Find Particular Record In The Database Table
//    public static User findRecordById(int id) {
//        User user = null;
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            user = (User) sessionObj.load(User.class, id);
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } 
//        return user;
//    }
//    
// // Method 4(b): This Method To Find Particular Record In The Database Table
//    public static boolean isUser(String email, String password) {
//         boolean result = false;
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            User user = (User) sessionObj
//            		.createQuery("select u from User u where email = :email and password = :password")
//            		.setParameter("email", email).setParameter("password", password).uniqueResult();
//            if(user != null) {
//            	result = true;
//            }
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } 
//        return result;
//    }

    
}
