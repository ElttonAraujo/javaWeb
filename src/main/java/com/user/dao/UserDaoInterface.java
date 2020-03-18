package com.user.dao;

import java.io.Serializable;
import java.util.List;

import com.user.model.User;

public interface UserDaoInterface <T, Id extends Serializable> {
 
    public void add(T entity);
     
    public void update(T entity);
     
    public T findById(Id id);
     
    public void delete(T entity);
     
    public List<T> findAll();

	public boolean isUser(T entity);
     

}
