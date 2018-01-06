package com.pmdb.servicelayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmdb.datalayer.entity.Role;
import com.pmdb.datalayer.entity.User;
import com.pmdb.datalayer.entity.UserRole;
import com.pmdb.datalayer.repository.UserRepository;
import com.pmdb.weblayer.model.UserAuthentication;
import com.pmdb.weblayer.model.UserRegistrationForm;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RoleService roleService;
	
	@Transactional
	public List<User> getUserList() {
		
		Iterator<User> users = userRepository.findAll().iterator();
		
		List<User> userList = new ArrayList<User>(); 
		
		while(users.hasNext()) {
			User user = users.next();
			userList.add(user);
		}
		
		return userList;
	}
	
	@Transactional
	public User findUser(Integer userId) {
		return userRepository.findByUserId(userId);
	}
	
	@Transactional
	public Boolean insertUser(UserRegistrationForm userRegistrationForm) {
		
		User user = new User();
		user.setEmail(userRegistrationForm.getEmail());
		user.setFirstName(userRegistrationForm.getFirstName());
		user.setLastName(userRegistrationForm.getLastName());
		user.setPassword(userRegistrationForm.getPassword());
		user.setUserName(userRegistrationForm.getUserName());
		
		Role role = roleService.findRole(userRegistrationForm.getUserRoleId());
		
		if(role == null) {
			
			role = new Role();
			role.setRoleName("DEFAULT_USER");
			role = roleService.insertRole(role);
		}
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		List<UserRole> userRoles = new ArrayList<UserRole>();
		userRoles.add(userRole);
		
		user.setUserRoles(userRoles);
		
		userRepository.save(user);
		
		return true;
	}
	
	@Transactional
	public User login(UserAuthentication userAuthentication) {
		
		User user = userRepository.findByUserNameAndPassword(userAuthentication.getUserName(), userAuthentication.getPassWord());
		
		if(user == null) {
			return null;
		}
		
		return user;
	}
	
	@Transactional
	public Boolean deleteUser(Integer userId) {
		
		userRepository.delete(userId);
		
		return true;
	}
	
	@Transactional
	public Boolean updateUser(Integer userId, UserRegistrationForm userRegistrationForm) {
		
		User user = userRepository.findByUserId(userId);
		
		user.setEmail(userRegistrationForm.getEmail());
		user.setFirstName(userRegistrationForm.getFirstName());
		user.setLastName(userRegistrationForm.getLastName());
		user.setPassword(userRegistrationForm.getPassword());
		user.setUserName(userRegistrationForm.getUserName());
		
		userRepository.save(user);
		
		return true;
	}
	
	@Transactional
	public List<String> getRoleNames() {
		
		List<Role> roles = roleService.getAllRoles();
		
		List<String> roleNameList = new ArrayList<String>();
		
		for(Role role : roles) {
			roleNameList.add(role.getRoleName());
		}
		
		return roleNameList;
	}
	
}
