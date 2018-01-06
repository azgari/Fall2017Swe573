package com.pmdb.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmdb.datalayer.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	
	public Role findByRoleId(Integer roleId);
	public Role findByRoleName(String roleName);
	
}
