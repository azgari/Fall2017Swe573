package com.pmdb.datalayer.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="users")
public class User implements Serializable{

	
	private static final long serialVersionUID = 8146337136453469146L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="password")
	private String password;
	
	@Column(name="user_name")
	private String userName;
		
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserRole> userRoles;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserMovie> userMovies;

	public List<UserMovie> getUserMovies() {
		return userMovies;
	}

	public void setUserMovies(List<UserMovie> userMovies) {
		this.userMovies = userMovies;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}
