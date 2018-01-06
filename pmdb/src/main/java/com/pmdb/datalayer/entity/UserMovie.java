package com.pmdb.datalayer.entity;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_movie_relations")
public class UserMovie implements Serializable {
	
	private static final long serialVersionUID = 890345L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "movie_relation_id")
	private Integer movieRelationId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="movie_id")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
	private User user;

	public Integer getMovieRelationId() {
		return movieRelationId;
	}

	public void setMovieRelationId(Integer movieRelationId) {
		this.movieRelationId = movieRelationId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
