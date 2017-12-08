package com.lmig.gfc.wimp.config;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@Configuration
public class SeedData {

	public SeedData(MovieRepository movieRepo, ActorRepository actorRepo) {
		Movie movie = movieRepo.save(new Movie("Avengers", new Date(2010, 8, 23), (long)3333333.33, "Marvel"));
		movieRepo.save(new Movie("Rocky", new Date(1777, 8, 23), (long)45323, "No clue"));
		Actor actor = actorRepo.save(new Actor("Tom", "Hanks", (long)1882, new Date(1882, 8, 23)));
		actorRepo.save(new Actor("Chris", "Pratt", (long)1999, new Date(1980, 7, 21)));
		
		// Populate join table
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(actor);
		movie.setActors(actors);
		
		movieRepo.save(movie);
	}
}
