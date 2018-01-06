package com.pmdb.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmdb.datalayer.entity.Movie;
import com.pmdb.datalayer.entity.UserMovie;

@Repository
public interface UserMovieRepository extends CrudRepository<UserMovie, Integer>{
	
}
