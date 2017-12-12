package com.lmig.gfc.wimp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.repositories.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorsApiController {
	
	private ActorRepository actorRepo;
	
	public ActorsApiController (ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
	}
	
	@GetMapping("")
	public List<ActorView> getAll() {
		List<Actor> actorList = actorRepo.findAll();
		ArrayList<ActorView> actorViews = new ArrayList<ActorView>();
		for (Actor actor: actorList) {
			actorViews.add(new ActorView(actor));
		}
		return actorViews;
	}
	
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED) 
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	@GetMapping("{id}")
	  public ActorView getOne(@PathVariable Long id) {  
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			return null;
		} 
		return new ActorView(actor);
	  }
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable Long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
	
	@DeleteMapping("{id}")
	  public Actor delete(@PathVariable Long id) {
		Actor actor = actorRepo.findOne(id);
		
		actorRepo.delete(id);
		
		return actor; 
	  }
	
	

}
