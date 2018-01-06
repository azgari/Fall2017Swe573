package com.pmdb.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmdb.datalayer.entity.UserRole;
import com.pmdb.datalayer.entity.User;

@Repository
public interface UserRoleRepository  extends CrudRepository<UserRole, User>{
	
	public UserRole findByUser(User user);
	
}





