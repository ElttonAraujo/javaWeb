package com.user.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.user.dao.UserService;
import com.user.model.Telephone;
import com.user.model.User;

public class DaoTest {
	
	private UserService userService;
	private String name;
	private String number;
	
	
	
	@Before
	public void getService() {
		userService = new UserService();
		name = "add";
		number = "99999999";
	}
	
	public void addUser() {
		
		Telephone telephone =  new Telephone(00, number, "cell");
		List<Telephone> telephones = new ArrayList<Telephone>();
		User user = new User();
		telephone.setUser(user);
		telephones.add(telephone);
		user.setName(name);
		user.setEmail("email");
		user.setPassword("password");
		user.setTelephones(telephones);
		userService.add(user);
	}
	
	@Test
	public void addTest() {
		
		addUser();
		List<User> users = userService.findAll();
		System.out.println(users.get(0).getName());
		assertEquals("The users are different", name, users.get(0).getName());
		assertEquals("The telephones are different", number, users.get(0).getTelephones().get(0).getNumber());
	}
	
	@Test
	public void update() {
		
		String nameUpdate = "update";
		String numberUpdate = "0000000";
		List<User> users = userService.findAll();
		User user = users.get(0);
		Telephone telefone = users.get(0).getTelephones().get(0);
		telefone.setNumber(numberUpdate);
		List<Telephone> telefones = new ArrayList<Telephone>();
		telefones.add(telefone);
		user.setName(nameUpdate);
		user.setTelephones(telefones);
		userService.update(user);
		List<User> updateUser = userService.findAll();
		
		assertEquals("The user not updated", nameUpdate, updateUser.get(0).getName());
		assertEquals("The telephonr not updated", numberUpdate, updateUser.get(0).getTelephones().get(0).getNumber());
		
		
	}
	
	@Test
	public void removeTest() {
		
		List<User> users = userService.findAll();
		assertEquals("User not add", 1, users.size());
		userService.delete(users.get(0).getId());
		assertEquals("User not removed", 0, userService.findAll().size());
		
	}
	
	@Test
	public void isUserTest() {
		User user = userService.findAll().get(0);
		List<Telephone> telephones = new ArrayList<Telephone>();
		User notUser = new User("not", "not", "not", telephones);
		assertEquals("Not is user", true, userService.isUser(user));
		assertNotEquals("Is a user", true, userService.isUser(notUser));
	}

}
