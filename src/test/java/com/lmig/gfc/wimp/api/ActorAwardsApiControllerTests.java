package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;

public class ActorAwardsApiControllerTests {
	
	private ActorAwardsApiController controller;
	@Mock private ActorRepository actorRepo;
	@Mock private AwardRepository awardRepo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
		controller = new ActorAwardsApiController(actorRepo, awardRepo);
	}
	
	@Test
	public void create_associates_actor_with_award(){
		// Arrange
		Actor actor = new Actor();
		actor.setAwards(new ArrayList<Award>());
		actor.setId(153L);
		when(actorRepo.findOne(153L)).thenReturn(actor);
		
		Award award = new Award();
		when(awardRepo.save(award)).thenReturn(award);
		
		
		// Act
		ActorView actual = controller.create(actor.getId(), award);
		
		// Assert
		System.out.println(actual.getAwards());
		assertThat(award.getActor()).isEqualTo(actor);
		verify(actorRepo).findOne(153L);
		verify(awardRepo).save(award);
		verify(actorRepo).save(actor);
		
	}
}
