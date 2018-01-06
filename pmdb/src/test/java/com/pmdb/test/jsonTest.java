package com.pmdb.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
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
import com.pmdb.weblayer.model.UserMovieRelation;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositorySpringConfiguration.class)
public class jsonTest {

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
	public void InsertMovie() throws JSONException, IOException {
		
		UserMovieRelation userMovieRelation = new UserMovieRelation();	
		userMovieRelation.setTitle("Dogville");
		String MovieName = userMovieRelation.getTitle();
		
    	String url = "http://www.omdbapi.com/?t=" + MovieName + "&apikey=6e0a08";   
   	    URL RawMovieData = null;
			RawMovieData = new URL(url);

   	    URLConnection yc = RawMovieData.openConnection();  
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                yc.getInputStream()));
       String inputLine = in.readLine();
       JSONObject obj = new JSONObject(inputLine);
		Movie movie = new Movie();
		movie.setTitle(userMovieRelation.getTitle());
		movie.setYear(obj.getInt("Year"));
		movie.setRuntime(obj.getString("Runtime"));
		movie.setGenre("xsa");//(obj.getString("Genre"));
		movie.setActors(obj.getString("Actors")); 
		movie.setDirector(obj.getString("Director"));
		movie.setPlot(obj.getString("Plot"));
		movie.setPoster(obj.getString("Poster"));
		movie.setImdbRating(obj.getString("imdbRating"));
		
		movieRepository.save(movie);
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
