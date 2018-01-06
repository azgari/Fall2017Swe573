package com.pmdb.servicelayer;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmdb.datalayer.entity.Movie;
import com.pmdb.datalayer.entity.User;
import com.pmdb.datalayer.entity.UserMovie;
import com.pmdb.datalayer.repository.MovieRepository;
import com.pmdb.datalayer.repository.UserMovieRepository;
import com.pmdb.datalayer.repository.UserRepository;
import com.pmdb.weblayer.model.UserMovieRelation;


@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserMovieRepository userMovieRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public List<Movie> getMovieList() {
		
		Iterator<Movie> movies = movieRepository.findAll().iterator();
		
		List<Movie> movieList = new ArrayList<Movie>(); 
		
		while(movies.hasNext()) {
			Movie movie = movies.next();
			movieList.add(movie);
		}
		
		return movieList;
	}
	
	@Transactional
	public Boolean insertMovie(UserMovieRelation userMovieRelation) {
		
		
		Movie movie = new Movie();
		
		movie.setTitle(userMovieRelation.getTitle());
		movie.setYear(userMovieRelation.getYear());
		movie.setRuntime(userMovieRelation.getRuntime());
		movie.setGenre(userMovieRelation.getGenre());
		movie.setActors(userMovieRelation.getActors());
		movie.setDirector(userMovieRelation.getDirector());
		movie.setPlot(userMovieRelation.getPlot());
		movie.setPoster(userMovieRelation.getPoster());
		movie.setImdbRating(userMovieRelation.getImdbRating());
		
		movieRepository.save(movie);
		
		
		return true;
	}
	
	@Transactional
	public List<Movie> getAllMovies() {
		
		List<Movie> movies = new ArrayList<Movie>();
		
		Iterator<Movie> iterator = movieRepository.findAll().iterator();
		
		while(iterator.hasNext()) {
			Movie movie = iterator.next();
			movies.add(movie);
		}
		
		return movies;
	}
	
	
	@Transactional
	public User insertMovie(Movie movie, Integer userId) {
		
		movieRepository.save(movie);
		
		User user = userRepo.findByUserId(userId);
		
		UserMovie userMovie = new UserMovie();
		userMovie.setUser(user);
		userMovie.setMovie(movie);
		
		userMovieRepository.save(userMovie);
		
		user.getUserMovies().add(userMovie);
		
		return user;
	}
	
}
