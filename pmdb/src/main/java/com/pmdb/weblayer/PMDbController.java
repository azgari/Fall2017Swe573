package com.pmdb.weblayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pmdb.datalayer.entity.Movie;
import com.pmdb.datalayer.entity.User;
import com.pmdb.servicelayer.UserService;
import com.pmdb.servicelayer.MovieService;
import com.pmdb.weblayer.model.UserAuthentication;
import com.pmdb.weblayer.model.UserRegistrationForm;
import com.pmdb.weblayer.model.UserMovieRelation;

@RestController
@RequestMapping("/pmdb/v1")
public class PMDbController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		return userService.getUserList();
	}
	
	@RequestMapping(value="/roles", method=RequestMethod.GET)
	public @ResponseBody List<String> getAllRoles() {
		return userService.getRoleNames();
	}
	
	@RequestMapping(value="/user/{userid}", method=RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("userid") Integer userId) {
		return userService.findUser(userId);
	}
	
	@RequestMapping(value="/user/{userid}", method=RequestMethod.DELETE)
	public @ResponseBody Boolean deleteUser(@PathVariable("userid") Integer userId) {
		return userService.deleteUser(userId);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public @ResponseBody Boolean insertUser(@RequestBody UserRegistrationForm userRegistrationForm) {
		return userService.insertUser(userRegistrationForm);
	}
	
	@RequestMapping(value="/login/user", method=RequestMethod.POST)
	public @ResponseBody User loginUser(@RequestBody UserAuthentication userAuthentication) {
		return userService.login(userAuthentication);
	}
	
	@RequestMapping(value="/user/update/{userid}", method=RequestMethod.POST)
	public @ResponseBody Boolean updateUser(@PathVariable("userid") Integer userId, @RequestBody UserRegistrationForm userRegistrationForm) {
		return userService.updateUser(userId, userRegistrationForm);
	}
	
	@RequestMapping(value="/user/{userid}/movies", method=RequestMethod.POST)
	public @ResponseBody Boolean insertMovie(@PathVariable("userid") Integer userId, @RequestBody UserMovieRelation userMovieRelation) throws IOException, JSONException {
    	
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
		userMovieRelation.setTitle(userMovieRelation.getTitle());
		userMovieRelation.setYear(obj.getInt("Year"));
		userMovieRelation.setRuntime("nedim");
		userMovieRelation.setGenre(obj.getString("Genre"));
		userMovieRelation.setActors(obj.getString("Actors")); 
		userMovieRelation.setDirector(obj.getString("Director"));
		userMovieRelation.setPlot(obj.getString("Plot"));
		userMovieRelation.setPoster(obj.getString("Poster"));
		userMovieRelation.setImdbRating(obj.getString("imdbRating"));

		return movieService.insertMovie(userMovieRelation);
	}
	
	@RequestMapping(value="/movies", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}
	
	@RequestMapping(value="/movie", method=RequestMethod.POST)
	public @ResponseBody User insertMovie(@RequestBody Movie movie, @RequestParam("userId") Integer userId) {
		return movieService.insertMovie(movie, userId);
	}
	
}
