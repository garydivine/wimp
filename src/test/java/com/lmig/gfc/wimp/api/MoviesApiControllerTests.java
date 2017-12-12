package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MoviesApiControllerTests {
	
	private MoviesApiController controller;
	@Mock private MovieRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MoviesApiController(repo);
	}
	
	@Test
	public void getAll_returns_list_of_movies() {
		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		when(repo.findAll()).thenReturn(movies);
		
		// Act
		List<Movie> actual = controller.getAll();
		
		
		// Assert 
		assertThat(actual).isSameAs(movies);
		verify(repo).findAll();
	}
	
	@Test
	public void create_saves_a_movie_and_returns_it() {
		// Arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);
		
		// Act
		Movie actual = controller.create(movie);
		
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);
	}
	
	@Test
	public void getOne_returns_movie_for_valid_id() {
		// Arrange
		Movie movie = new Movie();
		when(repo.findOne(7L)).thenReturn(movie);
		
		// Act
		Movie actual = controller.getOne(7L);
		
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findOne(7L);
	}
	
	@Test
	public void getOne_does_not_return_movie_because_of_invalid_id() {
		// Arrange
		
		// Act
		Movie actual = controller.getOne(7L);
		
		// Assert
		assertThat(actual).isNull();
		verify(repo).findOne(7L);
	}
	
	@Test 
	public void update_saves_movie() {
		// Arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);
		
		// Act
		Movie actual = controller.update(movie, 7L);
		
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);
		assertThat(movie.getId()).isEqualTo(7L);
		
	}
	
	@Test
	public void delete_gets_movie_and_removes_it() {
		// Arrange
		Movie movie = new Movie();
		when(repo.findOne(5L)).thenReturn(movie);
		
		// Act
		Movie actual = controller.delete(5L);
		
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findOne(5L);
		verify(repo).delete(5L);
	}

}
