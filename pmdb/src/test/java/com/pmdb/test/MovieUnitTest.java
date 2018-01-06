package com.pmdb.test;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.pmdb.datalayer.entity.Movie;
import com.pmdb.datalayer.entity.User;
import com.pmdb.datalayer.entity.UserMovie;
import com.pmdb.datalayer.repository.MovieRepository;
import com.pmdb.datalayer.repository.UserRepository;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositorySpringConfiguration.class)
public class MovieUnitTest {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
/*
	@Test
	public void selectMovieList() {
		
		Iterator<Movie> movies = movieRepository.findAll().iterator();
		
		boolean hasRecord = false;
		while(movies.hasNext()) {
			Movie new_movie = movies.next();
			System.out.println(new_movie.getTitle());
			hasRecord = true;
		}
		
		Assert.assertTrue(hasRecord);
	}
	*/
	
	@Test
	@Transactional
	@Rollback(false)
	public void createMovie() {

		User user = userRepository.findByUserId(1);
		
		Movie Test_Movie = new Movie();
		Test_Movie.setYear(2017);
		
		UserMovie userMovie = new UserMovie();
		userMovie.setMovie(Test_Movie);
		userMovie.setUser(user);
		
		List<UserMovie> userMovies = new ArrayList<UserMovie>();
		userMovies.add(userMovie);
		
		user.setUserMovies(userMovies);
		
		userRepository.save(user);
		
		Assert.assertTrue(user.getUserMovies().get(0).getMovie().getMovieId() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void updateMovie() {
		Movie Test_Movie = movieRepository.findByMovieId(6);
		Test_Movie.setYear(1972); 
		Test_Movie.setTitle("The Godfather");
	
		movieRepository.save(Test_Movie);
		
		Assert.assertTrue(Test_Movie.getYear() == 1972);
		Assert.assertTrue(Test_Movie.getTitle() == "The Godfather");
	}
		
	
}
