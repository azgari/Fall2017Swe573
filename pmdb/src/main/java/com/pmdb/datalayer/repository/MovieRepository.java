package com.pmdb.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmdb.datalayer.entity.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{

	// {findBy + Attribute Name} is automatically anticipated Spring format 
	Movie findByMovieId(Integer movieId);
	
}
