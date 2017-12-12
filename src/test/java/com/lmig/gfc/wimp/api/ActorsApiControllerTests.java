package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;

public class ActorsApiControllerTests {

	private ActorsApiController controller;
	@Mock private ActorRepository repo;
	
	@Before
	public void setUp() {
		repo = mock(ActorRepository.class);
		controller = new ActorsApiController(repo);
	}
	
	@Test
	public void getAll_returns_list_of_actors() {
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Actor actor = new Actor();
		actor.setId(77L);
		actors.add(actor);
		when(repo.findAll()).thenReturn(actors);
		
		// Act
		List<ActorView> actual = controller.getAll();
		
		// Assert
		assertThat(actual.get(0).getId()).isEqualTo(actor.getId());
		verify(repo).findAll();
		assertThat(actual.size()).isEqualTo(1);
	}
	
	
	@Test
	public void create_saves_an_actor_and_returns_it() {
		// Arrange
		Actor actor = new Actor();
		when(repo.save(actor)).thenReturn(actor);
		
		// Act
		Actor actual = controller.create(actor);
		
		// Assert
		assertThat(actual).isSameAs(actor);
		verify(repo).save(actor);
	}
	
	@Test
	public void getOne_returns_actor_for_valid_id() {
		// Arrange
		Actor actor = new Actor();
		when(repo.findOne(7L)).thenReturn(actor);
		
		// Act
		ActorView actual = controller.getOne(7L);
		
		// Assert
		assertThat(actual.getId()).isEqualTo(actor.getId());
		verify(repo).findOne(7L);
	}
	
	@Test
	public void getOne_does_not_return_movie_because_of_invalid_id() {
		// Arrange
		
		// Act
		ActorView actual = controller.getOne(9L);
		
		// Assert
		assertThat(actual).isNull();
		verify(repo).findOne(9L);
	}
	
	@Test 
	public void update_saves_actors() {
		// Arrange
		Actor actor = new Actor();
		when(repo.save(actor)).thenReturn(actor);
		
		// Act
		Actor actual = controller.update(actor, 7L);
		
		// Assert
		assertThat(actual).isSameAs(actor);
		verify(repo).save(actor);
		assertThat(actor.getId()).isEqualTo(7L);
		
	}
	
	@Test
	public void delete_gets_actor_and_removes_it() {
		// Arrange
		Actor actor = new Actor();
		when(repo.findOne(5L)).thenReturn(actor);
		
		// Act
		Actor actual = controller.delete(5L);
		
		// Assert
		assertThat(actual).isSameAs(actor);
		verify(repo).findOne(5L);
		verify(repo).delete(5L);
	}
	
}
