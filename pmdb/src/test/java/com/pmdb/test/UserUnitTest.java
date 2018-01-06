package com.pmdb.test;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pmdb.datalayer.config.RepositorySpringConfiguration;
import com.pmdb.datalayer.entity.Role;
import com.pmdb.datalayer.entity.User;
import com.pmdb.datalayer.entity.UserRole;
import com.pmdb.datalayer.repository.UserRepository;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositorySpringConfiguration.class)
public class UserUnitTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	public void insertUser() {
		
		User AdminUser = new User();
		AdminUser.setFirstName("Nedim");
		AdminUser.setLastName("Azgari");
		AdminUser.setPassword("12345");
		AdminUser.setUserName("azgari");
		AdminUser.setEmail("azgari.nedim@gmail.com");
		
		System.out.println(AdminUser.getEmail());
		
		Role role1 = new Role();
		role1.setRoleName("Administator");
		
		UserRole userRole1 = new UserRole();
		userRole1.setUser(AdminUser);
		userRole1.setRole(role1);
		
		///////////////////////////////////////////////
		/*
		Role role2 = new Role();
		role2.setRoleName("ADMIN2");
		
		UserRole userRole2 = new UserRole();
		userRole2.setUser(user);
		userRole2.setRole(role2);
		
		List<UserRole> userRoles = new ArrayList<UserRole>();
		userRoles.add(userRole1);
		userRoles.add(userRole2);
		
		user.setUserRoles(userRoles);
		
		userRepository.save(user);
		*/
		
		List<UserRole> userRoles = new ArrayList<UserRole>();
		userRoles.add(userRole1);
		
		AdminUser.setUserRoles(userRoles);
		
		userRepository.save(AdminUser);
		
		Assert.assertNotNull(AdminUser.getUserId());
		
	}
	/*
	public void selectUser() {
		
		User user = userRepository.findByUserId(1);
		
		Assert.assertTrue(user.getUserRoles().size() > 0);
	}
	*/
}
