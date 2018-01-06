package com.pmdb.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmdb.datalayer.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	// {findBy + Attribute Name} is automatically anticipated Spring format 
	User findByUserId(Integer userId);
	User findByUserNameAndPassword(String userName, String password);
	
}
