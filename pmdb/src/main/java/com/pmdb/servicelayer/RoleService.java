package com.pmdb.servicelayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmdb.datalayer.entity.Role;
import com.pmdb.datalayer.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public List<Role> getAllRoles() {
		
		Iterator<Role> roles = roleRepository.findAll().iterator();
		
		List<Role> roleList = new ArrayList<Role>();
		
		while(roles.hasNext()) {
			Role role = roles.next();
			roleList.add(role);
		}
		
		return roleList;
	}
	
	@Transactional
	public Role findRole(String name) {
		
		return roleRepository.findByRoleName(name);
		
	}
	
	@Transactional
	public Role findRole(Integer id) {
		
		return roleRepository.findByRoleId(id);
		
	}
	
	@Transactional
	public Role insertRole(Role role) {
		
		roleRepository.save(role);
		
		return role;
	}
	
}
