package com.pmdb.datalayer.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie implements Serializable{
	
	
	private static final long serialVersionUID = 8146337136453469146L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="movie_id")
	private Integer movieId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name="runtime")
	private String runtime;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="director")
	private String director;
	
	@Column(name="actors")
	private String actors;
	
	@Column(name="plot")
	private String plot;
	
	@Column(name="poster")
	private String poster;
	
	@Column(name="imdb_rating")
	private String imdbRating;

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	
}
