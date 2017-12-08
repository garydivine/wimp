package com.lmig.gfc.wimp.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;

public class ActorView {

	private Actor actor;
	
	public ActorView(Actor actor) {
		this.actor = actor;
	}
	
	public Long getId() {
		return actor.getId();
	}
	
	public String getFirstName() {
		return actor.getFirstName();
	}
	
	public String getLastName() {
		return actor.getLastName();
	}
	
	public Long getActiveSinceYear() {
		return actor.getActiveSinceYear();
	}
	
	public Date getBirthDate() {
		return actor.getBirthDate();
	}
	
	public List<MovieView> getMovies() {
	ArrayList<MovieView> views = new ArrayList<MovieView>();
	for (Movie movie: actor.getMovies()) {
		views.add(new MovieView(movie));
	}
	
	return views;
}
	
}
