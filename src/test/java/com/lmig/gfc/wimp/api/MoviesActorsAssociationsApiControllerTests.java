package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MoviesActorsAssociationsApiControllerTests {

	private MoviesActorsAssociationsApiController controller;
	@Mock private MovieRepository movieRepo;
	@Mock private ActorRepository actorRepo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
		controller = new MoviesActorsAssociationsApiController(movieRepo, actorRepo);
	}
	
	@Test
	public void create_associates_actor_with_movie() {
		// Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		movie.setActors(new ArrayList<Actor>());
		when(movieRepo.findOne(147L)).thenReturn(movie);
		when(actorRepo.findOne(153L)).thenReturn(actor);
		
		// Act
		Movie actual = controller.create(147L, 153L);
		
		
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
		assertThat(movie.getActors()).contains(actor);
		verify(movieRepo).findOne(147L);
		verify(actorRepo).findOne(153L);
	}
	
	@Test
	public void create_does_not_associate_actor_with_movie() {
		// Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		movie.setActors(new ArrayList<Actor>());
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(actor);
		movie.setActors(actors);
		when(movieRepo.findOne(147L)).thenReturn(movie);
		when(actorRepo.findOne(153L)).thenReturn(actor);
		
		// Act
		Movie actual = controller.create(147L, 153L);
		
		
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo, never()).save(movie);
		assertThat(movie.getActors()).hasSize(1);
		verify(movieRepo).findOne(147L);
		verify(actorRepo).findOne(153L);
		
	}
	
}
